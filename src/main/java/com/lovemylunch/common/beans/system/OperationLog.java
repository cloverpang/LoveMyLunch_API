package com.lovemylunch.common.beans.system;

import java.util.Date;

public class OperationLog {
    private String operationId;
    private String operationUrl;
    private String operationName;
    private String requestParameters;

    private String operationReturn;
    private String operationException;
    private String operationUser;
    private String operationToken;
    private Date createTime;

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationUrl() {
        return operationUrl;
    }

    public void setOperationUrl(String operationUrl) {
        this.operationUrl = operationUrl;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(String requestParameters) {
        this.requestParameters = requestParameters;
    }

    public String getOperationReturn() {
        return operationReturn;
    }

    public void setOperationReturn(String operationReturn) {
        this.operationReturn = operationReturn;
    }

    public String getOperationException() {
        return operationException;
    }

    public void setOperationException(String operationException) {
        this.operationException = operationException;
    }

    public String getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    public String getOperationToken() {
        return operationToken;
    }

    public void setOperationToken(String operationToken) {
        this.operationToken = operationToken;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
