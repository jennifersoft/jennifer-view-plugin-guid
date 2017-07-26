package com.aries.ctrl;

import com.aries.ctrl.model.GUIDData;
import com.aries.data.viewdata.TransactionViewData;
import com.aries.view.LogUtil;
import com.aries.view.api.BusinessSet;
import com.aries.view.api.data.Business;
import com.aries.view.core.nio.DataServerException;
import com.aries.view.domain.Agent;
import com.aries.view.helper.ViewFormatHelper;
import com.aries.view.service.DomainService;
import com.aries.view.service.mng.AgentService;
import com.aries.view.service.mng.BusinessService;
import com.aries.view.service.perf.TextDataService;
import com.aries.view.service.perf.XViewService;
import com.aries.view.web.BaseController;
import com.aries.view.domain.DataServer;
import aries.core.OID;
import aries.def.ErrorTypeDef;
import aries.lang.TABLE;
import aries.util.IpUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = { "/plugin" })
public class GUIDController {
    @Autowired
    TextDataService textDataService;
    @Autowired
    XViewService xviewService;
    @Autowired
    DomainService domainService;
    @Autowired
    AgentService agentService;
    @Autowired
    BusinessService businessService;

    @RequestMapping(value = { "/guid" }, method = RequestMethod.GET)
    public ModelAndView mainPage(WebRequest request) throws JSONException
    {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap map = modelAndView.getModelMap();

        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        String guid = request.getParameter("guid");
        String time_pattern = request.getParameter("time_pattern");

        long start_time = 0;
        long end_time = 0;

        if(time_pattern != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(time_pattern);

            try {
                Date sdate = sdf.parse(stime);
                start_time = sdate.getTime();

                Date edate = sdf.parse(etime);
                end_time = edate.getTime();
            } catch (ParseException e) {
                LogUtil.warn(e);
            }

        } else {
            start_time = (stime != null) ? Long.parseLong(stime) : -1;
            end_time = (etime != null) ? Long.parseLong(etime) : -1;
        }

        map.put("stime", start_time);
        map.put("etime", end_time);
        map.put("guid", (guid != null) ? guid : "");

        return modelAndView;
    }

    @RequestMapping(value = { "/guid/list" }, method = RequestMethod.GET)
    @ResponseBody
    public List<GUIDData> getDailyDataList(@RequestParam long stime, @RequestParam long etime, @RequestParam String guid) throws DataServerException {
        List<GUIDData> list = new ArrayList<GUIDData>();
        DataServer[] domains = domainService.connectedList();

        for(DataServer domain : domains) {
            List<TransactionViewData> records = xviewService.searchByGuidList(domain.getSid(), stime, etime, guid.getBytes());

            for (int i = 0; i < records.size(); i++) {
                TransactionViewData r = records.get(i);
                list.add(getTransactionData(domain.getSid(), r));
            }
        }

        return list;
    }

    private GUIDData getTransactionData(short domain_id, TransactionViewData r) throws DataServerException {
        GUIDData point = new GUIDData();

        int oid = r.instanceOid;
        Agent agent = agentService.findAgent(domain_id, oid);

        // Business 관련 코드
        OID[] bizObjOids = r.bizOidSet.getAllOids();
        int[] bizOids = new int[bizObjOids.length];
        for(int i = 0; i < bizObjOids.length; i++) {
            bizOids[i] = bizObjOids[i].getOid();
        }

        point.setDomainId(domain_id);
        point.setDomainName(domainService.get(domain_id).getShortName());
        point.setInstanceId(oid);
        point.setInstanceName(agent != null ? agent.getShortName() : oid+"");
        point.setBusiness(getTargetBusiness(domain_id, bizOids));
        point.setTxid(String.valueOf(r.id));
        point.setGuid((r.guidOrNull != null) ? new String(r.guidOrNull) : "");
        point.setClientIp(IpUtil.toString(r.ipAddressOrNull));
        point.setClientId(r.wmonidOrZero);

        point.setUserId((r.userIdOrNull != null) ? new String(r.userIdOrNull) : "");
        point.setNetworkTime(r.networkTimeOrZero);
        point.setFrontendTime(r.frontEndElapsedTimeOrZero);
        point.setStartTime(r.agentEndTime - r.elapsedTime);
        point.setEndTime(r.agentEndTime);

        point.setResponseTime(r.elapsedTime);
        point.setCpuTime(r.cpuTime);
        point.setSqlTime(r.sqlTime);
        point.setFetchTime(r.fetchTime);
        point.setExternalcallTime(r.externalCallTime);
        if(r.mostImportantErrorTypeOrZero != 0) {
            point.setErrorType(ErrorTypeDef.names.getName(r.mostImportantErrorTypeOrZero));
        } else {
            point.setErrorType("");
        }

        point.setApplicationName(r.applicationName);

        return point;
    }

    private List<Short> getTargetBusiness(short domain_id, int[] bizOids) throws DataServerException {
        List<Business> bizSet = getBusiness(domain_id).getList();
        List<Short> newBizSet = new ArrayList<Short>();

        for(int i = 0; i < bizOids.length; i++) {
            for(int j = 0; j < bizSet.size(); j++) {
                if(bizOids[i] == bizSet.get(j).getOid()) {
                    Business biz = bizSet.get(j);
                    newBizSet.add(biz.getBusinessId());
                }
            }
        }

        return newBizSet;
    }

    private BusinessSet getBusiness(short domain_id) throws DataServerException {
        BusinessSet result = new BusinessSet();
        List<Business> business = new ArrayList<Business>();
        TABLE table = businessService.groupTreeList(domain_id);
        List<Map<String, Object>> list = new ViewFormatHelper().formatGroupListToList(table);

        for(int i = 0; i < list.size(); i++) {
            Map<String, Object> m = list.get(i);
            Business b = new Business();

            b.setBusinessId(Short.parseShort((String) m.get("bizId")));
            b.setName((String) m.get("shortName"));
            b.setDescription((String) m.get("longName"));
            b.setBadResponseTime(Integer.parseInt((String) m.get("badResponseTimeLimit")));
            b.setBusinessIndex((String) m.get("treeIndex"));
            b.setOid((Integer) m.get("oid"));

            String ruleList = (String) m.get("ruleList");
            b.setRuleList(ruleList.split("\\|"));

            business.add(b);
        }

        result.setList(business);

        return result;
    }
}