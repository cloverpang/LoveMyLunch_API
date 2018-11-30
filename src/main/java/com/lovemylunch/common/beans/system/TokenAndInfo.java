package com.lovemylunch.common.beans.system;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class TokenAndInfo implements Serializable{
    private AdminUser adminUser;
    private TokenSession tokenSession;
    private String operationCenterCode;//运营中心代码

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public TokenSession getTokenSession() {
        return tokenSession;
    }

    public void setTokenSession(TokenSession tokenSession) {
        this.tokenSession = tokenSession;
    }

    public String getOperationCenterCode() {
        return operationCenterCode;
    }

    public void setOperationCenterCode(String operationCenterCode) {
        this.operationCenterCode = operationCenterCode;
    }
}
