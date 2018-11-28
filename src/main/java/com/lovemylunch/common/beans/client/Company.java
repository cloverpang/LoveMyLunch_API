package com.lovemylunch.common.beans.client;

import java.util.Date;

public class Company {
    private String companyId;//公司ID
    private String companyName;//公司名称
    private String companyCode;//公司代码 (AsiaInspection)
    private String operationCenterCode;//运营中心代码

    private String companyAddress;//address
    private String companyLogoPath;// logo
    private Integer status;// 状态 0 正常 1 注销

    private Date joinTime;//加入时间

    private Date createTime;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getOperationCenterCode() {
        return operationCenterCode;
    }

    public void setOperationCenterCode(String operationCenterCode) {
        this.operationCenterCode = operationCenterCode;
    }

    public String getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(String companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}
