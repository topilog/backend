package cn.styxs.personalweb.configuration;

import cn.styxs.personalweb.service.PermissionService;
import cn.styxs.personalweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomInterceptorConfigurer implements WebMvcConfigurer {
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessControlInterceptor(userService)).addPathPatterns("/**");
        registry.addInterceptor(new PermissionControlInterceptor(userService, permissionService)).addPathPatterns("/**");
    }
}
