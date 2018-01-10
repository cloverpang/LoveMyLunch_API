package com.lovemylunch.api.dao.mybatis.mapper;

import com.lovemylunch.common.beans.distribut.DistributionForm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface DistributionFormMapper {
    public DistributionForm get(String id);

    public DistributionForm getByNumber(String number);

    public void insert(DistributionForm distributionForm);

    public void update(DistributionForm distributionForm);

    public void delete(String id);

    public List<DistributionForm> search(Map<String, Object> param);

    public int count(Map<String, Object> param);
}
