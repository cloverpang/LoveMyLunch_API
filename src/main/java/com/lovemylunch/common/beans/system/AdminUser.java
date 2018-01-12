package com.lovemylunch.common.beans.system;

import java.util.Date;

public class AdminUser {
    private String admin_id;
    private String admin_login;
    private String admin_password;
    private String admin_name;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
