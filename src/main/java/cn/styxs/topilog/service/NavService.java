package cn.styxs.topilog.service;

import cn.styxs.topilog.model.NavTabItem;
import cn.styxs.topilog.model.SiteInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 提供导航栏相关功能
 */
@Service
@Slf4j
public class NavService {
    @Autowired
    ConfigService configService;

    List<NavTabItem> navTabItems;

    @PostConstruct
    public void genNavTabs() {
        SiteInfo siteInfo = configService.getSiteInfo();
        List<String> titles = siteInfo.getNavTabs();
        List<String> urls = siteInfo.getNavUrls();
        log.info("tabs Num: " +titles.size()+"; urls Num: " +urls.size());
        navTabItems = new ArrayList<>();
        for (int i = 0; i < (titles.size() > urls.size()? urls.size() : titles.size()); i++) {
            navTabItems.add(NavTabItem.builder().title(titles.get(i)).navigateToUrl(urls.get(i)).build());
        }
        siteInfo.setNavItems(navTabItems);
    }

    public List<NavTabItem> getTabItems() {
        return navTabItems;
    }
}
