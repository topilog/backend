package cn.styxs.topilog.service;

import cn.styxs.topilog.model.SiteInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
class ConfigServiceTest {
    @Autowired
    ConfigService configService;

    @Test
    public void testSiteConfigModel() {
        SiteInfo siteInfo = configService.getSiteInfo();
        Assert.notNull(siteInfo, "can't get siteConfig");
        Assert.notNull(siteInfo.getSiteName(), "siteName can't be null");
        log.info(siteInfo.toString());
    }
}