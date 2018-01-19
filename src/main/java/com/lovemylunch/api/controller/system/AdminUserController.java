package com.lovemylunch.api.controller.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovemylunch.api.service.AdminUserService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.annotation.PermssionSecured;
import com.lovemylunch.common.beans.annotation.TokenSecured;
import com.lovemylunch.common.beans.system.AdminUser;
import com.lovemylunch.common.beans.system.LoginBean;
import com.lovemylunch.common.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
//@RequestMapping("/AdminUser")
@Api(tags = {"AdminUser"}, description = "AdminUser APIs")
public class AdminUserController {
    protected Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private AdminUserService adminUserService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/admin/login")
    @ApiOperation(value = "Admin user login", response = Boolean.class)
    public ResponseEntity<ApiCallResult> adminLogin(@RequestBody(required = true) LoginBean loginBean, HttpServletRequest request,
                                                    HttpServletResponse response) throws JsonProcessingException {
        String adminLogin = loginBean.getAccount();
        String adminPassword = loginBean.getPassword();
        logger.info("invoke: " + "/adminUser/login/" + adminLogin + "/" +  adminPassword);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = false;
            AdminUser adminUser = new AdminUser();
            adminUser.setAdmin_login(adminLogin);
            adminUser.setAdmin_password(adminPassword);
            AdminUser originAdminUser = adminUserService.login(adminUser);
            if(null != originAdminUser && StringUtils.isNotEmpty(originAdminUser.getAdmin_id())){
                excute = true;
            }
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("Admin user login failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="adminUser_updatePassword")
    @RequestMapping(value = "/adminUser/updatePassword", method = RequestMethod.PUT)
    @ApiOperation(value = "Admin updatePassword", response = Boolean.class)
    public ResponseEntity<ApiCallResult> updatePassword(@RequestBody(required = true) Map map) {
        String adminLogin = (String) map.get("adminLogin");
        String adminPassword = (String) map.get("adminPassword");
        String newAdminPassword = (String) map.get("newAdminPassword");
        logger.info("invoke: " + "/adminUser/updatePassword/" + adminLogin + "/" +  adminPassword);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = false;
            AdminUser adminUser = new AdminUser();
            adminUser.setAdmin_login(adminLogin);
            adminUser.setAdmin_password(adminPassword);
            AdminUser originAdminUser = adminUserService.login(adminUser);
            if(null != originAdminUser && StringUtils.isNotEmpty(originAdminUser.getAdmin_id())){
                originAdminUser.setAdmin_password(newAdminPassword);
                adminUserService.updatePassword(originAdminUser);
                excute = true;
            }
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("Admin user updatePassword failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="adminUser_delete")
    @RequestMapping(value = "/adminUser/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete adminUser API", response = boolean.class)
    public ResponseEntity<ApiCallResult> deleteAdminUser(
            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/adminUser/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = adminUserService.delete(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("delete adminUser failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="adminUser_getAll")
    @RequestMapping(value={"/adminUsers"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search adminUser API", response = AdminUser.class,responseContainer = "List")
    public ResponseEntity<ApiCallResult> search(){
        logger.info("invoke: " + "/adminUsers");
        ApiCallResult result = new ApiCallResult();
        try{
            List<AdminUser> adminUsers = adminUserService.getAll();
            result.setContent(adminUsers);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get all adminUser failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="adminUser_update_backend_permission")
    @RequestMapping(value = "/adminUser/updateBackendPermissions", method = RequestMethod.PUT)
    @ApiOperation(value = "Admin updateBackendPermissions", response = Boolean.class)
    public ResponseEntity<ApiCallResult> updateBackendPermissions(@RequestBody(required = true) Map map) {
        String admin_login = (String) map.get("admin_login");
        List<String> admin_backend_permission = ( List<String>) map.get("admin_backend_permission");
        String backend_permissions = StringUtils.join(admin_backend_permission,",");
        logger.info("invoke: " + "/adminUser/updateBackendPermissions/" + admin_login );
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = false;
            AdminUser adminUser = new AdminUser();
            adminUser.setAdmin_login(admin_login);
            adminUser.setBackend_permissions(backend_permissions);
            adminUserService.updateBackendPermissions(adminUser);
            excute = true;
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("Admin user update backend permissions failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="adminUser_update_front_permission")
    @RequestMapping(value = "/adminUser/updateFrontendPermissions", method = RequestMethod.PUT)
    @ApiOperation(value = "Admin updateBackendPermissions", response = Boolean.class)
    public ResponseEntity<ApiCallResult> updateFrontendPermissions(@RequestBody(required = true) Map map) {
        String admin_login = (String) map.get("admin_login");
        List<String> admin_frontend_permission = ( List<String>) map.get("admin_frontend_permission");
        List<String> admin_backend_permission = ( List<String>) map.get("admin_backend_permission");
        String frontend_permission = StringUtils.join(admin_frontend_permission,",");
        String backend_permissions = StringUtils.join(admin_backend_permission,",");
        logger.info("invoke: " + "/adminUser/updateFrontendPermissions/" + admin_login );
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = false;
            AdminUser adminUser = new AdminUser();
            adminUser.setAdmin_login(admin_login);
            adminUser.setFrontend_permissions(frontend_permission);
            adminUser.setBackend_permissions(backend_permissions);
            adminUserService.updateFrontendPermissions(adminUser);
            excute = true;
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("Admin user update front permissions failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
