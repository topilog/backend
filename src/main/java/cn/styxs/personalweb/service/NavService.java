package cn.styxs.personalweb.service;

import cn.styxs.personalweb.model.NavTabItem;
import cn.styxs.personalweb.model.SiteConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NavService {
    @Autowired
    ConfigService configService;

    List<NavTabItem> navTabItems;

    @PostConstruct
    public void genNavTabs() {
        SiteConfig siteConfig = configService.getSiteConfig();
        List<String> titles = siteConfig.getNavTabs();
        List<String> urls = siteConfig.getNavUrls();
        log.info("tabs Num: " +titles.size()+"; urls Num: " +urls.size());
        navTabItems = new ArrayList<>();
        for (int i = 0; i < (titles.size() > urls.size()? urls.size() : titles.size()); i++) {
            navTabItems.add(NavTabItem.builder().title(titles.get(i)).navigateToUrl(urls.get(i)).build());
        }
    }

    public List<NavTabItem> getTabItems() {
        return navTabItems;
    }
}
