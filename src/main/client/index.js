import $ from 'jquery';
import jui from 'juijs';
import ComboComp from 'juijs-ui/src/components/combo';
import DatePickerComp from 'juijs-ui/src/components/datepicker';
import XTableComp from 'juijs-grid/src/components/xtable';
import extension from 'aries-extension-js';
import './index.less';

var ui_dates = null, ui_hours = null, ui_minutues = null, ui_table = null;

extension.setup({
    hostName: ""
});

jui.use(ComboComp, DatePickerComp, XTableComp);

jui.ready([ "ui.combo", "ui.datepicker", "grid.xtable" ], function(combo, datepicker, xtable) {
    var guid = $("#guid").val(),
        stime = parseInt($("#param_stime").val()),
        etime = parseInt($("#param_etime").val()),
        popupWidth = $("#guid-main").width();

    ui_table = xtable("#table_detail", {
        fields: [
            "domainName", "instanceName", "business", "txid", "guid", "clientIp", "clientId", "userId", "networkTime", "frontendTime",
            "startTime", "endTime", "collectionTime", "responseTime", "sqlTime", "externalcallTime", "fetchTime", "cpuTime",
            "errorType", "applicationName"
        ],
        resize: true,
        colshow: [ 1, 2, 4, 6, 8, 11, 12, 13, 14, 15, 16, 17, 18, 19 ],
        sort: true,
        sortIndex: 12,
        width: popupWidth + (popupWidth * 0.15),
        scrollWidth: popupWidth,
        rowHeight: 26,
        sortLoading: true,
        scrollHeight: $("#content").height() - 200,
        buffer: "vscroll",
        event: {
            select: function(row, e) {
                this.select(row.index);

                extension.popup("xview", {
                    domainId: row.data.domainId,
                    txIds: [ row.data.txid ],
                    startTime: row.data.collectionTime - row.data.responseTime,
                    endTime: row.data.collectionTime
                });
            }
        },
        tpl: {
            none: $("#tpl_none").html(),
            row: $("#tpl_row").html(),
            loading: $("#tpl_loading").html(),
            menu: $("#tpl_menu").html()
        }
    });

    ui_dates = datepicker("#s_datepicker,#e_datepicker", {
        titleFormat: "yyyy. MM",
        format: "yyyy-MM-dd",
        event: {
            select: function(date, e) {
                if(this.index == 0) {
                    $("#btn_s_date").find("span").html(date);
                    $(ui_dates[0].root).hide();
                } else {
                    $("#btn_e_date").find("span").html(date);
                    $(ui_dates[1].root).hide();
                }
            }
        },
        tpl: {
            date: $("#tpl_date").html()
        }
    });

    ui_hours = combo("#s_hours,#e_hours");
    ui_minutues = combo("#s_minutes,#e_minutes");

    $("#btn_s_date").on("click", function(e) {
        setDatePickerEvent(this, ui_dates[0], ui_dates[1]);
    });
    $("#btn_e_date").on("click", function(e) {
        setDatePickerEvent(this, ui_dates[1], ui_dates[0]);
    });

    // 검색 이벤트 설정
    $("#btn_search").on("click", function(e) {
        var guid = $("#guid").val(),
            times = getSearchTimes();

        if(guid != "") {
            loadGuidDataList(guid, times.start, times.end);
        } else {
            alert($("#msg_alert1").val());
        }
    });

    // 테이블 컬럼 보이기/숨기기 기능
    $("#btn_manage_column").on("click", function(e) {
        var offset = $(e.target).offset();

        ui_table.toggleColumnMenu($('body').width() - 170, offset.top + 23);
        return false;
    });

    // 전체 트랜잭션 팝업 띄우기
    $("#btn_all_transaction").on("click", function(e) {
        var times = getSearchTimes(),
            datas = ui_table.listData(),
            unitsBySid = {};

        if(datas.length > 0) {
            for (var i = 0; i < datas.length; i++) {
                var sid = datas[i].domainId;

                if (!unitsBySid[sid]) {
                    unitsBySid[sid] = [];
                }

                unitsBySid[sid].push(datas[i].txid);
            }

            extension.popup("xview.multidomain", {
                txIds: unitsBySid,
                startTime: times.start,
                endTime: times.end
            });
        }

        return false;
    });

    // 날짜 컴포넌트 기본값 설정
    if(stime != -1) {
        setDateParams(0, stime);
    }
    if(etime != -1) {
        setDateParams(1, etime);
    }

    // 모든 매개변수 값이 있을 경우 자동 조회
    if(guid != "" && stime != -1 && etime != -1) {
        loadGuidDataList(guid, stime, etime);
    }

    ui_dates[0].emit("select", [ ui_dates[0].getFormat() ]);
    ui_dates[1].emit("select", [ ui_dates[1].getFormat() ]);
});

function setDatePickerEvent(elem, start, end) {
    if($(start.root).css("display") == "block") {
        $(start.root).hide();
    } else {
        var pos = $(elem).offset();

        $(start.root).css({
            left: pos.left - 19,
            display: "block"
        });
        $(end.root).hide();
    }
}

function loadGuidDataList(guid, stime, etime) {
    $.ajax({
        url:"/plugin/guid/list",
        method: "GET",
        data: {
            guid: guid,
            stime: stime,
            etime: etime
        },
        success: function(data) {
            ui_table.update(data);
            $(".total-count").find("span").html(data.length);
        },
        error: function(e) {
        }
    });
}

function setDateParams(index, time) {
    var date = new Date(time);

    ui_dates[index].select(time);
    ui_hours[index].setValue(date.getHours());
    ui_minutues[index].setValue(date.getMinutes());
}

function getSearchTimes() {
    var sdate = ui_dates[0].getDate(),
        edate = ui_dates[1].getDate();

        sdate.setHours(ui_hours[0].getValue());
        sdate.setMinutes(ui_minutues[0].getValue());
        sdate.setSeconds(0);
        sdate.setMilliseconds(0);
        edate.setHours(ui_hours[1].getValue());
        edate.setMinutes(ui_minutues[1].getValue());
        edate.setSeconds(1);
        edate.setMilliseconds(1);

    return {
        start: sdate.getTime(),
        end: edate.getTime()
    }
}

function printDateStr(time) {
    const date = new Date(time);
    return `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()} ${date.getMilliseconds()}`;
}

window.ui_dates = ui_dates;
window.ui_hours = ui_hours;
window.ui_minutues = ui_minutues;
window.ui_table = ui_table;
window.printDateStr = printDateStr;
