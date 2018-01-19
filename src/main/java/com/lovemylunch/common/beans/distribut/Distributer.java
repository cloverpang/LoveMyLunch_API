package com.lovemylunch.common.beans.distribut;

import java.util.Date;

//配送者
public class Distributer {
    private String distributerId;
    private String distributerName;
    private String mobile;
    private String photoPath;
    private Integer distributerTimes;
    private Integer status;// 0 正常 1 已注销
    private String operationCenterCode;//运营中心代码
    private Date createTime;

    public String getDistributerId() {
        return distributerId;
    }

    public void setDistributerId(String distributerId) {
        this.distributerId = distributerId;
    }

    public String getDistributerName() {
        return distributerName;
    }

    public void setDistributerName(String distributerName) {
        this.distributerName = distributerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getDistributerTimes() {
        return distributerTimes;
    }

    public void setDistributerTimes(Integer distributerTimes) {
        this.distributerTimes = distributerTimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
