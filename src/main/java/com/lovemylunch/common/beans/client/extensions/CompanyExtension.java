package com.lovemylunch.common.beans.client.extensions;

import com.lovemylunch.common.beans.client.Company;

/**
 * Created by Administrator on 2017/12/25.
 */
public class CompanyExtension  extends Company{
    private Integer companyCustomerQuantity; //服务人数

    public Integer getCompanyCustomerQuantity() {
        return companyCustomerQuantity;
    }

    public void setCompanyCustomerQuantity(Integer companyCustomerQuantity) {
        this.companyCustomerQuantity = companyCustomerQuantity;
    }
}
