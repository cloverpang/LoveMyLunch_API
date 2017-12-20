package com.lovemylunch.api.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;
import com.lovemylunch.common.beans.client.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CompanyMapper {
    public Company get(String id);

    public void insert(Company Company);

    public void update(Company Company);

    public void delete(String id);

    public List<Company> search(Map<String, Object> param);

    public int count(Map<String, Object> param);
}
