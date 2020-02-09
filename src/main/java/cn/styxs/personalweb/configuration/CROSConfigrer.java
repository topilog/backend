package cn.styxs.personalweb.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CROSConfigrer implements WebMvcConfigurer {
    @Value("${debug}")
    public Boolean debug;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (debug) {
            registry.addMapping("/**");
        }
    }
}
