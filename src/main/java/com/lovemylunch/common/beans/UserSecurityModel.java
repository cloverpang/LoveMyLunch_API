package com.lovemylunch.common.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserSecurityModel implements Serializable {
    private static final long serialVersionUID = -2774031423483716160L;
    private String userId;
    private String firstName;
    private String lastName;
    private String departmentId;
    private String department;
    private String status;
    private Date joinDate;
    private String position;
    private String employeeType;
    private String email;
    private String phone;
    private String login;
    private String token;
    private final Map<String, Set<String>> aclMap = new HashMap();

    public UserSecurityModel() {
    }

    public Map<String, Set<String>> getAclMap() {
        return this.aclMap;
    }

    public boolean containsModule(String module) {
        return module != null && !module.isEmpty()?this.aclMap.containsKey(module.trim().toLowerCase()):false;
    }

    public boolean containsRole(String module, String role) {
        if(module != null && !module.isEmpty() && role != null && !role.isEmpty()) {
            Set roles = (Set)this.aclMap.get(module.trim().toLowerCase());
            return roles != null && roles.contains(role.trim().toLowerCase());
        } else {
            return false;
        }
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getJoinDate() {
        return this.joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployeeType() {
        return this.employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String toString() {
        return "UserSecurityModel [userId=" + this.userId + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", departmentId=" + this.departmentId + ", department=" + this.department + ", status=" + this.status + ", joinDate=" + this.joinDate + ", position=" + this.position + ", employeeType=" + this.employeeType + ", email=" + this.email + ", phone=" + this.phone + ", login=" + this.login + ", aclMap=" + this.aclMap + "]";
    }
}
