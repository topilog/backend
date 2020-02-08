package cn.styxs.personalweb.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class VueBuildPathConfigurer implements WebMvcConfigurer {
    @Value("${debug}")
    private Boolean debug;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/vue/dist");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (debug) {
            registry.addMapping("/**");
        }
    }
}
