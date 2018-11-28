package com.lovemylunch.common.beans.dashboard;

public class Dashboard {
    private Integer dishQuantity;
    private Integer orderQuantity;
    private Integer companyQuantity;
    private Integer customerQuantity;

    private Double averageOrderPrice;
    private Double totalIncome;

    public Integer getDishQuantity() {
        return dishQuantity;
    }

    public void setDishQuantity(Integer dishQuantity) {
        this.dishQuantity = dishQuantity;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Integer getCompanyQuantity() {
        return companyQuantity;
    }

    public void setCompanyQuantity(Integer companyQuantity) {
        this.companyQuantity = companyQuantity;
    }

    public Integer getCustomerQuantity() {
        return customerQuantity;
    }

    public void setCustomerQuantity(Integer customerQuantity) {
        this.customerQuantity = customerQuantity;
    }

    public Double getAverageOrderPrice() {
        return averageOrderPrice;
    }

    public void setAverageOrderPrice(Double averageOrderPrice) {
        this.averageOrderPrice = averageOrderPrice;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }
}
