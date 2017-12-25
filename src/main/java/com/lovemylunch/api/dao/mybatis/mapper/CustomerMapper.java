package com.lovemylunch.api.dao.mybatis.mapper;

import com.lovemylunch.common.beans.client.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface CustomerMapper {
    public Customer get(String id);

    public Customer getByLogin(String login);

    public void insert(Customer customer);

    public void update(Customer customer);

    public void delete(String id);

    public List<Customer> search(Map<String, Object> param);

    public int count(Map<String, Object> param);
}
