package com.lovemylunch.api.service.impl;


import com.lovemylunch.api.dao.mybatis.mapper.AdminUserMapper;
import com.lovemylunch.common.beans.system.AdminUser;
import com.lovemylunch.api.service.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class AdminUserServiceImpl implements AdminUserService{
    protected Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser getByLogin(String login) throws Exception {
        try{
            return adminUserMapper.getByLogin(login);
        }catch (Exception e){
            throw new Exception("get Admin user failed!",e);
        }
    }

    @Override
    public AdminUser getById(String id) throws Exception {
        try{
            return adminUserMapper.getById(id);
        }catch (Exception e){
            throw new Exception("get Admin user failed!",e);
        }
    }

    @Override
    public AdminUser login(AdminUser adminUser) throws Exception{
        try{
            return adminUserMapper.login(adminUser);
        }catch (Exception e){
            throw new Exception("admin login  failed!",e);
        }
    }

    @Override
    public void updatePassword(AdminUser adminUser) throws Exception {
        try{
            adminUserMapper.updatePassword(adminUser);
        }catch (Exception e){
            throw new Exception("update admin user password failed!",e);
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        try{
            adminUserMapper.delete(id);
            return true;
        }catch (Exception e){
            logger.error("failed on delete Admin user : " + e.getMessage());
            throw new Exception("failed on delete Admin user : ",e);
        }
    }

    @Override
    public List<AdminUser> getAll(String center) {
        return adminUserMapper.getAll(center);
    }

    @Override
    public void updateBackendPermissions(AdminUser adminUser) throws Exception {
        try{
            adminUserMapper.updateBackendPermissions(adminUser);
        }catch (Exception e){
            throw new Exception("update admin backend permissions failed!",e);
        }
    }

    @Override
    public void updateFrontendPermissions(AdminUser adminUser) throws Exception {
        try{
            adminUserMapper.updateFrontendPermissions(adminUser);
        }catch (Exception e){
            throw new Exception("update admin frontend permissions failed!",e);
        }
    }
}
