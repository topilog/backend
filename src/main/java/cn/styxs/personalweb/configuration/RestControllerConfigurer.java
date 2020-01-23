package cn.styxs.personalweb.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestControllerConfigurer implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 所有RestController路径统一添加api前缀
        configurer.addPathPrefix("/api", c -> c.isAnnotationPresent(RestController.class));
    }
}
