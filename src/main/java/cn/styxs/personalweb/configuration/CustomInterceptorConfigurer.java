package cn.styxs.personalweb.configuration;

import cn.styxs.personalweb.service.PermissionService;
import cn.styxs.personalweb.service.URIAccessService;
import cn.styxs.personalweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 在这里配置自定义拦截器
 */
@Configuration
public class CustomInterceptorConfigurer implements WebMvcConfigurer {
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    URIAccessService uriAccessService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessControlInterceptor(uriAccessService, userService)).addPathPatterns("/**");
        registry.addInterceptor(new PermissionControlInterceptor(uriAccessService, userService, permissionService)).addPathPatterns("/**");
    }
}
