package cn.styxs.topilog.service;

import cn.styxs.topilog.model.SiteInfo;
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
    SiteInfo siteInfo;

    @PostConstruct
    public void logSiteConfig() {
        log.info(siteInfo.toString());
    }

    public SiteInfo getSiteInfo() {
        return siteInfo;
    }
}
