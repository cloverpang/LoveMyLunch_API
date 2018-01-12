package com.lovemylunch.api.controller.system;

import com.lovemylunch.api.service.AdminUserService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.system.AdminUser;
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

    @RequestMapping(value = "/adminUser/login", method = RequestMethod.POST)
    @ApiOperation(value = "admin login", response = Boolean.class)
    public ResponseEntity<ApiCallResult> adminLogin(@RequestBody(required = true) Map map) {
        String adminLogin = (String) map.get("adminLogin");
        String adminPassword = (String) map.get("adminPassword");
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

    @RequestMapping(value = "//adminUser/updatePassword", method = RequestMethod.POST)
    @ApiOperation(value = "admin updatePassword", response = Boolean.class)
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
}
