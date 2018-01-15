package com.lovemylunch.common.beans.system;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class TokenSession implements Serializable {
    private static final long serialVersionUID = -5044418229603611305L;
    private String id;
    private String userId;
    String userType = "client";
    private String token;
    private String validBefore;

    public TokenSession() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValidBefore() {
        return this.validBefore;
    }

    public void setValidBefore(String validBefore) {
        this.validBefore = validBefore;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String toString() {
        return "TokenSession{id=\'" + this.id + '\'' + ", userId=\'" + this.userId + '\'' + ", userType=\'" + this.userType + '\'' + ", token=\'" + this.token + '\'' + ", validBefore=\'" + this.validBefore + '\'' + '}';
    }
}
