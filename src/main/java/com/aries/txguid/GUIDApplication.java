package com.aries.txguid;

import aries.lang.*;
import com.aries.data.lang.profile.PiData;
import com.aries.data.viewdata.TransactionViewData;
import com.aries.extension.starter.PluginStarter;
import com.aries.view.core.nio.DataServerException;
import com.aries.view.dao.file.RealTimeXViewConfigDao;
import com.aries.view.domain.Agent;
import com.aries.view.domain.DataServer;
import com.aries.view.service.DomainService;
import com.aries.view.service.ViewDataResponseFuture;
import com.aries.view.service.mng.AgentService;
import com.aries.view.service.mng.BusinessService;
import com.aries.view.service.perf.TextDataService;
import com.aries.view.service.perf.XViewService;
import com.aries.view.wrapper.PiDataFormat;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class GUIDApplication extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PluginStarter()).addPathPatterns("/plugin/**");
    }

    @Bean
    public TextDataService getTextDataService() {
        return new TextDataService() {
            @Override
            public TYPE_MAP getTexts(short i, long l, long l1, int[] ints, byte b) throws DataServerException {
                return null;
            }

            @Override
            public String getTextV2(short i, long l, long l1, int i1, byte b) throws DataServerException {
                return null;
            }

            @Override
            public Map<Integer, String> getTextsV2(short i, long l, long l1, int[] ints, byte b) throws DataServerException {
                return null;
            }
        };
    }

    @Bean
    public XViewService getXViewService() {
        return new XViewService() {
            @Override
            public List<TransactionViewData> searchByTimeRange(short i, int[] ints, long l, long l1) throws DataServerException {
                return null;
            }

            @Override
            public ViewDataResponseFuture<List<TransactionViewData>> searchKeys(short i, long l, long l1, long[] longs) throws DataServerException {
                return null;
            }

            @Override
            public TransactionViewData searchByTxid(short i, long l, long l1, long l2) throws DataServerException {
                return null;
            }

            @Override
            public TransactionViewData searchByTxid(short i, long l, long l1) throws DataServerException {
                return null;
            }

            @Override
            public TransactionViewData searchCallerByRemoteCallKey(short i, long l, String s) throws DataServerException {
                return null;
            }

            @Override
            public TransactionViewData searchReceiverByRemoteCallKey(short i, long l, String s) throws DataServerException {
                return null;
            }

            @Override
            public Map<String, Object> searchByGuidListWithStatus(short i, long l, byte[] bytes) throws DataServerException {
                return null;
            }

            @Override
            public List<TransactionViewData> searchByGuidList(short i, long l, long l1, byte[] bytes) throws DataServerException {
                return null;
            }

            @Override
            public TMAP searchByTxidClient(short i, long l, long l1, long l2) throws DataServerException {
                return null;
            }

            @Override
            public Map<String, Object> getProfileV2(short i, long l, long l1, long l2) throws DataServerException {
                return null;
            }

            @Override
            public List<Object> listTargetMethodProfile(short i, boolean b) throws DataServerException {
                return null;
            }

            @Override
            public Map<String, Object> getTargetMethodProfile(short i, boolean b, byte b1, String s, byte b2) throws DataServerException {
                return null;
            }

            @Override
            public boolean setTargetMethodProfile(short i, boolean b, byte b1, String s, byte b2, boolean b3, int[] ints, int[] ints1, String[] strings) throws DataServerException {
                return false;
            }

            @Override
            public boolean removeTargetMethodProfile(short i, boolean b, byte b1, String s, byte b2) throws DataServerException {
                return false;
            }

            @Override
            public List<Object> listTargetUserIdProfile(short i) throws DataServerException {
                return null;
            }

            @Override
            public Map<String, Object> getTargetUserIdProfile(short i, byte b, String s) throws DataServerException {
                return null;
            }

            @Override
            public boolean setTargetUserIdProfile(short i, byte b, String s, boolean b1, int[] ints, int[] ints1) throws DataServerException {
                return false;
            }

            @Override
            public boolean removeTargetUserIdProfile(short i, byte b, String s) throws DataServerException {
                return false;
            }

            @Override
            public List<Object> listTargetGuidProfile(short i) throws DataServerException {
                return null;
            }

            @Override
            public Map<String, Object> getTargetGuidProfile(short i, byte b, String s) throws DataServerException {
                return null;
            }

            @Override
            public boolean setTargetGuidProfile(short i, byte b, String s, boolean b1, int[] ints, int[] ints1) throws DataServerException {
                return false;
            }

            @Override
            public boolean removeTargetGuidProfile(short i, byte b, String s) throws DataServerException {
                return false;
            }

            @Override
            public List<Map<String, Object>> getProfileAPIList(short i) throws DataServerException {
                return null;
            }

            @Override
            public boolean setProfileAPI(short i, byte b, String s, String s1) throws DataServerException {
                return false;
            }

            @Override
            public boolean removeProfileAPI(short i, byte b, String s) throws DataServerException {
                return false;
            }

            @Override
            public TLIST getApiSettingList(short i) throws DataServerException {
                return null;
            }

            @Override
            public List<Map<String, Object>> getXViewErrorList(short i, long l, long l1, long l2, long l3) throws DataServerException {
                return null;
            }

            @Override
            public PiDataFormat[] formatProfile(short i, long l, long l1, PiData[] piData, byte b) throws DataServerException {
                return new PiDataFormat[0];
            }
        };
    }

    @Bean
    public DomainService getDomainService() {
        return new DomainService() {
            @Override
            public DataServer get(short i) {
                return null;
            }

            @Override
            public DataServer[] list() {
                return new DataServer[0];
            }

            @Override
            public DataServer[] listCacheLimit() {
                return new DataServer[0];
            }

            @Override
            public DataServer[] accessibleList(String s) throws JSONException {
                return new DataServer[0];
            }

            @Override
            public DataServer[] connectedList() {
                return new DataServer[0];
            }

            @Override
            public DataServer getDefaultOrNull() {
                return null;
            }

            @Override
            public Map<Short, Boolean> statusList() {
                return null;
            }

            @Override
            public Map<Short, DataServer> toMap() {
                return null;
            }

            @Override
            public short getDefaultSid() {
                return 0;
            }

            @Override
            public boolean isValid(short i) {
                return false;
            }

            @Override
            public boolean saveDomain(DataServer dataServer) {
                return false;
            }

            @Override
            public boolean updateDomain(DataServer dataServer) {
                return false;
            }

            @Override
            public boolean updateCacheLimit(RealTimeXViewConfigDao.XViewConfig xViewConfig) {
                return false;
            }

            @Override
            public boolean removeDomain(short i) {
                return false;
            }

            @Override
            public RealTimeXViewConfigDao.XViewConfig getMinTimeForDomain(short i) {
                return null;
            }

            @Override
            public Map<String, Object> getInstanceManagePolicy(short i) throws DataServerException {
                return null;
            }

            @Override
            public boolean setInstanceManagePolicy(short i, boolean b, int i1, boolean b1, boolean b2, byte b3, byte b4, int i2) throws DataServerException {
                return false;
            }

            @Override
            public boolean setInstanceNamingRule(short i, byte b, boolean b1) throws DataServerException {
                return false;
            }

            @Override
            public boolean setInstanceOrders(short i, int[] ints, short[] shorts) {
                return false;
            }

            @Override
            public Map<Integer, Short> getInstanceOrders(short i) {
                return null;
            }

            @Override
            public Map<String, Boolean> getSqlParameterCollection(short i) throws DataServerException {
                return null;
            }

            @Override
            public boolean setSqlParameterCollection(short i, boolean b, boolean b1) throws DataServerException {
                return false;
            }

            @Override
            public boolean setDomainGroups(String[] strings, short[] shorts, String[] strings1) {
                return false;
            }

            @Override
            public List<Map<String, Object>> getDomainGroups() {
                return null;
            }

            @Override
            public boolean isDomainGroupUsage() {
                return false;
            }

            @Override
            public void setDomainGroupUsage(boolean b) {

            }

            @Override
            public List<Map<String, Object>> getDomainGroupsWithUncategory(short[] shorts) {
                return null;
            }

            @Override
            public List<Map<String, Object>> getUnknownDomainGroups(short[] shorts) {
                return null;
            }
        };
    }

    @Bean
    public AgentService getAgentService() {
        return new AgentService() {
            @Override
            public boolean isPendingAgentOptions(short i, int i1) throws DataServerException {
                return false;
            }

            @Override
            public boolean applyPendingAgentOptions(short i, int i1) throws DataServerException {
                return false;
            }

            @Override
            public TABLE getSessionList(short i) throws DataServerException {
                return null;
            }

            @Override
            public TABLE getAllList(short i) throws DataServerException {
                return null;
            }

            @Override
            public TABLE getAllInstanceList(short i) throws DataServerException {
                return null;
            }

            @Override
            public TABLE[] getAllInstanceList(short[] shorts) throws DataServerException {
                return new TABLE[0];
            }

            @Override
            public List<Agent> findHost(short i) throws DataServerException {
                return null;
            }

            @Override
            public List<Agent> getOnlyAgentList(short i) throws DataServerException {
                return null;
            }

            @Override
            public List<Agent> getOnlyAgentList(short i, List<Integer> list) throws DataServerException {
                return null;
            }

            @Override
            public List<Agent> getOnlyHostList(short i, List<Integer> list) throws DataServerException {
                return null;
            }

            @Override
            public ViewDataResponseFuture<TABLE> getWorkList(short i) throws DataServerException {
                return null;
            }

            @Override
            public TABLE[] getWorkList(short[] shorts) throws DataServerException {
                return new TABLE[0];
            }

            @Override
            public boolean modifyName(short i, int i1, String s, String s1) throws DataServerException {
                return false;
            }

            @Override
            public List<Agent> findPeriodAgentList(short i, long l, long l1, List<Integer> list) throws DataServerException {
                return null;
            }

            @Override
            public List<Agent> findPeriodHostList(short i, String s, String s1, List<Integer> list) throws DataServerException {
                return null;
            }

            @Override
            public Map<Integer, String> getShortNames(short i, INTS ints) throws DataServerException {
                return null;
            }

            @Override
            public boolean remove(short i, int i1) throws DataServerException {
                return false;
            }

            @Override
            public List<Map<String, Object>> getAgentUpgradeList(short i) throws DataServerException {
                return null;
            }

            @Override
            public byte setAgentUpgrade(short i, int i1, String s, byte[] bytes) throws DataServerException {
                return 0;
            }

            @Override
            public List<Agent> findAgent(short i) throws DataServerException {
                return null;
            }

            @Override
            public Agent findAgent(short i, int i1) throws DataServerException {
                return null;
            }

            @Override
            public List<Map<String, Object>> getAgentLogList(short i, int i1) throws DataServerException {
                return null;
            }

            @Override
            public List<Map<String, Object>> findPeriodAgentLogList(short i, int i1) throws DataServerException {
                return null;
            }

            @Override
            public byte[] getAgentLogFile(short i, int i1, String s) throws DataServerException {
                return new byte[0];
            }

            @Override
            public List<Map<String, Object>> getOrderedAgentList(short i, short i1) throws DataServerException {
                return null;
            }

            @Override
            public List<Map<String, Object>> getOrderedSessionList(short i, short i1) throws DataServerException {
                return null;
            }

            @Override
            public Map<Integer, Map<String, Object>> getSessionMapByOid(short i, short i1) throws DataServerException {
                return null;
            }

            @Override
            public void sortAgentList(short i, List<Map<String, Object>> list) {

            }

            @Override
            public int[] getAliveOidList(short i) throws DataServerException {
                return new int[0];
            }
        };
    }

    @Bean
    public BusinessService getBusinessService() {
        return new BusinessService() {
            @Override
            public INTS getLevel1Oids(short i) throws DataServerException {
                return null;
            }

            @Override
            public Map<Integer, String> getShortNames(short i, INTS ints) throws DataServerException {
                return null;
            }

            @Override
            public TABLE groupTreeList(short i) throws DataServerException {
                return null;
            }

            @Override
            public ViewDataResponseFuture<TABLE> getWorkList(short i) throws DataServerException {
                return null;
            }

            @Override
            public boolean groupTreeSave(short i, TABLE table) throws DataServerException {
                return false;
            }

            @Override
            public void save(short i, int[] ints) {

            }

            @Override
            public int[] list(short i) {
                return new int[0];
            }

            @Override
            public void save(String s, short i, int[] ints) {

            }

            @Override
            public int[] list(String s, short i) {
                return new int[0];
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(GUIDApplication.class, args);
    }
}