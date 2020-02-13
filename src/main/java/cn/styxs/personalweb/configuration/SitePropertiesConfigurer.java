package cn.styxs.personalweb.configuration;

import cn.styxs.personalweb.model.SiteConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:site.properties", encoding = "utf-8")
@Slf4j
public class SitePropertiesConfigurer {
    @Bean
    public SiteConfig siteConfig() {
        SiteConfig siteConfig = new SiteConfig();
        return siteConfig;
    }
}
