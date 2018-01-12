package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.system.AdminUser;

import java.util.List;

public interface AdminUserService {
    AdminUser login(AdminUser adminUser) throws Exception;
    void updatePassword(AdminUser adminUser) throws Exception;
    boolean delete(String id) throws Exception;
    public List<AdminUser> getAll();
}
