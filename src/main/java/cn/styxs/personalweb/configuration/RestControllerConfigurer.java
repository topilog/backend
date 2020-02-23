package cn.styxs.personalweb.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 所有RestController RequestMapping路径统一添加api前缀
 */
@Configuration
public class RestControllerConfigurer implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 所有RestController路径统一添加api前缀
        configurer.addPathPrefix("/api", c -> c.isAnnotationPresent(RestController.class));
    }
}
