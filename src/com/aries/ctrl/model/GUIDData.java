package com.aries.ctrl.model;

import java.util.List;

public class GUIDData {
    private short domainId;
    private String domainName;
    private int instanceId;
    private String instanceName;
    private List<Short> business;
    private String txid;
    private String guid;
    private String clientIp;
    private long clientId;
    private String userId;
    private int networkTime;
    private int frontendTime;
    private long startTime;
    private long endTime;
    private long collectionTime;
    private int responseTime;
    private int cpuTime;
    private int sqlTime;
    private int fetchTime;
    private int externalcallTime;
    private String errorType;
    private String applicationName;

    public GUIDData() {
    }

    public long getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(long collectionTime) {
        this.collectionTime = collectionTime;
    }

    public short getDomainId() {
        return this.domainId;
    }

    public void setDomainId(short domainId) {
        this.domainId = domainId;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public int getInstanceId() {
        return this.instanceId;
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceName() {
        return this.instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public List<Short> getBusiness() {
        return this.business;
    }

    public void setBusiness(List<Short> business) {
        this.business = business;
    }

    public String getTxid() {
        return this.txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public long getClientId() {
        return this.clientId;
    }

    public void setClientId(long l) {
        this.clientId = l;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNetworkTime() {
        return this.networkTime;
    }

    public void setNetworkTime(int networkTime) {
        this.networkTime = networkTime;
    }

    public int getFrontendTime() {
        return this.frontendTime;
    }

    public void setFrontendTime(int frontendTime) {
        this.frontendTime = frontendTime;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getCpuTime() {
        return this.cpuTime;
    }

    public void setCpuTime(int cpuTime) {
        this.cpuTime = cpuTime;
    }

    public int getSqlTime() {
        return this.sqlTime;
    }

    public void setSqlTime(int sqlTime) {
        this.sqlTime = sqlTime;
    }

    public int getFetchTime() {
        return this.fetchTime;
    }

    public void setFetchTime(int fetchTime) {
        this.fetchTime = fetchTime;
    }

    public int getExternalcallTime() {
        return this.externalcallTime;
    }

    public void setExternalcallTime(int externalcallTime) {
        this.externalcallTime = externalcallTime;
    }

    public String getErrorType() {
        return this.errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
