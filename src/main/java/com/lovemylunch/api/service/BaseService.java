package com.lovemylunch.api.service;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement// 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven/>
public class BaseService {
}
