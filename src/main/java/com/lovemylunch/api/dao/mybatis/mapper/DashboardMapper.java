package com.lovemylunch.api.dao.mybatis.mapper;

import com.lovemylunch.common.beans.dashboard.CreateCount;
import com.lovemylunch.common.beans.dashboard.Quantity;
import com.lovemylunch.common.beans.dashboard.Dashboard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface DashboardMapper {
    Dashboard getDashborad(String center);

    List<CreateCount> getOrderCreated(Map<String, Object> param);

    List<CreateCount> getCustomerCreated(Map<String, Object> param);
}
