package com.lovemylunch.api.dao.mybatis.mapper;

import com.lovemylunch.common.beans.system.AccessTokenDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AccessTokenMapper extends
        ConflictSupportMapper<AccessTokenDO>{
    public void saveToken(AccessTokenDO accessTokenDO);

    public AccessTokenDO getByUserId(String user_id);

    public AccessTokenDO getByToken(String token);

    public void refreshByToken(AccessTokenDO accessTokenDO);

    public void removeByToken(String token);

    public void removeByExpiry(String expiry);
}
