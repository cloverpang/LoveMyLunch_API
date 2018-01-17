package com.lovemylunch;

import com.lovemylunch.api.interceptor.PermissionCheckInterceptor;
import com.lovemylunch.api.interceptor.TokenCheckInterceptor;

import com.lovemylunch.api.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurerAdapter {

    @Value("${base.url}")
    private String baseURL;

    @Autowired
    AdminUserService adminUserService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        TokenCheckInterceptor tokenCheckInterceptor = new TokenCheckInterceptor();
        tokenCheckInterceptor.setBaseURL(baseURL);
        PermissionCheckInterceptor permissionCheckInterceptor = new PermissionCheckInterceptor();
        permissionCheckInterceptor.setAdminUserService(adminUserService);
        registry.addInterceptor(tokenCheckInterceptor).addPathPatterns("/**");
        registry.addInterceptor(permissionCheckInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
