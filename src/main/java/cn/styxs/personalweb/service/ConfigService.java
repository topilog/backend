package cn.styxs.personalweb.service;

import cn.styxs.personalweb.model.SiteConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 提供站点配置相关功能
 */
@Service
@Slf4j
public class ConfigService {
    @Autowired
    SiteConfig siteConfig;

    @PostConstruct
    public void logSiteConfig() {
        log.info(siteConfig.toString());
    }

    public SiteConfig getSiteConfig() {
        return siteConfig;
    }
}
