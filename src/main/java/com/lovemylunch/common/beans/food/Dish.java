package com.lovemylunch.common.beans.food;

import java.util.Date;
import java.util.List;

public class Dish {
    public Dish() {
    }

    private String dishId;//菜品主键ID
    private String dishName;//菜品名称

    private String dishType; // 类型 dish,soup ...

    private Integer dishPrice;//菜品价格 1,3,5,7,9,11 or 2,4,6,8,10,12
    private Integer status;//菜品是否开放 0 开放 1 不开放

    private String dishImageSmall;//菜品小图
    private String dishImageMiddle;
    private String dishImageLarge;

    private List<String> dishImageList;//图像列表

    private String dishStyle; //菜品风格
    private String component; //菜品组成

    private Integer pepper; // 辣椒指数 0,1,2,3,4,5

    private String operationCenterCode;//运营中心代码
    private Date createTime;

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public Integer getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Integer dishPrice) {
        this.dishPrice = dishPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDishImageSmall() {
        return dishImageSmall;
    }

    public void setDishImageSmall(String dishImageSmall) {
        this.dishImageSmall = dishImageSmall;
    }

    public String getDishImageMiddle() {
        return dishImageMiddle;
    }

    public void setDishImageMiddle(String dishImageMiddle) {
        this.dishImageMiddle = dishImageMiddle;
    }

    public String getDishImageLarge() {
        return dishImageLarge;
    }

    public void setDishImageLarge(String dishImageLarge) {
        this.dishImageLarge = dishImageLarge;
    }

    public List<String> getDishImageList() {
        return dishImageList;
    }

    public void setDishImageList(List<String> dishImageList) {
        this.dishImageList = dishImageList;
    }

    public String getDishStyle() {
        return dishStyle;
    }

    public void setDishStyle(String dishStyle) {
        this.dishStyle = dishStyle;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getPepper() {
        return pepper;
    }

    public void setPepper(Integer pepper) {
        this.pepper = pepper;
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
