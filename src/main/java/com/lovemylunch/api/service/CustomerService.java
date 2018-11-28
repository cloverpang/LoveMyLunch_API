package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.client.Customer;
import com.lovemylunch.common.beans.client.extensions.CustomerExtension;

import java.util.List;

public interface CustomerService {
    Customer get(String id) throws Exception;

    Customer getByLogin(String login) throws Exception;

    Boolean insert(Customer customer) throws Exception;

    Boolean update(Customer customer) throws Exception;

    Boolean delete(String id) throws Exception;

    PageBean<Customer> page(String conditionsStr, int pageSize, int pageNo,
                           String sortColumn, String sortType) throws Exception;

    List<Customer> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception;
}
