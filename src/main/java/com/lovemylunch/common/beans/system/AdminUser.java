package com.lovemylunch.common.beans.system;

import java.util.Date;

public class AdminUser {
    private String admin_id;
    private String admin_login;
    private String admin_password;
    private String admin_name;
    private String frontend_permissions;
    private String backend_permissions;
    private String operationCenterCode;//运营中心代码
    private Date createTime;

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_login() {
        return admin_login;
    }

    public void setAdmin_login(String admin_login) {
        this.admin_login = admin_login;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getFrontend_permissions() {
        return frontend_permissions;
    }

    public void setFrontend_permissions(String frontend_permissions) {
        this.frontend_permissions = frontend_permissions;
    }

    public String getBackend_permissions() {
        return backend_permissions;
    }

    public void setBackend_permissions(String backend_permissions) {
        this.backend_permissions = backend_permissions;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperationCenterCode() {
        return operationCenterCode;
    }

    public void setOperationCenterCode(String operationCenterCode) {
        this.operationCenterCode = operationCenterCode;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "admin_id='" + admin_id + '\'' +
                ", admin_login='" + admin_login + '\'' +
                ", admin_password='" + admin_password + '\'' +
                ", admin_name='" + admin_name + '\'' +
                ", frontend_permissions='" + frontend_permissions + '\'' +
                ", backend_permissions='" + backend_permissions + '\'' +
                ", operationCenterCode='" + operationCenterCode + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
