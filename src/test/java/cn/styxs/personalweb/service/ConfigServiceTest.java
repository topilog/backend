package cn.styxs.personalweb.service;

import cn.styxs.personalweb.model.SiteConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ConfigServiceTest {
    @Autowired
    ConfigService configService;

    @Test
    public void testSiteConfigModel() {
        SiteConfig siteConfig = configService.getSiteConfig();
        Assert.notNull(siteConfig, "can't get siteConfig");
        Assert.notNull(siteConfig.getSiteName(), "siteName can't be null");
        log.info(siteConfig.toString());
    }
}