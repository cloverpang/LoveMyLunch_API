package com.lovemylunch.api.dao.mybatis.mapper;

import com.lovemylunch.common.beans.system.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AdminUserMapper {
    public AdminUser getByLogin(String login);
    public AdminUser getById(String id);
    public AdminUser login(AdminUser adminUser);
    public List<AdminUser> getAll();
    public void updatePassword(AdminUser adminUser);
    public void updateBackendPermissions(AdminUser adminUser);
    public void updateFrontendPermissions(AdminUser adminUser);
    public void delete(String id);
}
