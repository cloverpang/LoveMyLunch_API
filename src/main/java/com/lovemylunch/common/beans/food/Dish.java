package com.lovemylunch.common.beans.food;

import java.util.Date;
import java.util.List;

public class Dish {
    public Dish() {
    }

    private String dishId;//��Ʒ����ID
    private String dishName;//��Ʒ����

    private String dishType; // ���� dish,soup ...

    private Integer dishPrice;//��Ʒ�۸� 1,3,5,7,9,11 or 2,4,6,8,10,12
    private Integer status;//��Ʒ�Ƿ񿪷� 0 ���� 1 ������

    private String dishImageSmall;//��ƷСͼ
    private String dishImageMiddle;
    private String dishImageLarge;

    private List<String> dishImageList;//ͼ���б�

    private String dishStyle; //��Ʒ���
    private String component; //��Ʒ���

    private Integer pepper; // ����ָ�� 0,1,2,3,4,5

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
}
