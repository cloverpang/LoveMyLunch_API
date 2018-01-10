package com.lovemylunch.common.beans.order;

public class SumItem {
    private String dishId;
    private String dishName;
    private String dishImageSmall;
    private Integer dishPrice;
    private Integer dishQuantity;
    private Integer dishTotalPrice;

    public SumItem() {
    }

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

    public String getDishImageSmall() {
        return dishImageSmall;
    }

    public void setDishImageSmall(String dishImageSmall) {
        this.dishImageSmall = dishImageSmall;
    }

    public Integer getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Integer dishPrice) {
        this.dishPrice = dishPrice;
    }

    public Integer getDishQuantity() {
        return dishQuantity;
    }

    public void setDishQuantity(Integer dishQuantity) {
        this.dishQuantity = dishQuantity;
    }

    public Integer getDishTotalPrice() {
        return dishTotalPrice;
    }

    public void setDishTotalPrice(Integer dishTotalPrice) {
        this.dishTotalPrice = dishTotalPrice;
    }
}
