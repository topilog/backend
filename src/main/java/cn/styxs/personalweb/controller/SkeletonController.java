package cn.styxs.personalweb.controller;

import cn.styxs.personalweb.model.NavTabItem;
import cn.styxs.personalweb.model.SiteConfig;
import cn.styxs.personalweb.service.ConfigService;
import cn.styxs.personalweb.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 用于传递Thymeleaf引擎所需要公共部分数据，例如导航栏、站点名字、Footer中内容等
 */
@ControllerAdvice
public class SkeletonController {
    @Autowired
    NavService navService;
    @Autowired
    ConfigService configService;

    @ModelAttribute(name = "navTabItems")
    public List<NavTabItem> getNavTabItems() {
        return navService.getTabItems();
    }

    @ModelAttribute(name = "siteName")
    public String getSiteTitle() {
        return configService.getSiteConfig().getSiteName();
    }

    @ModelAttribute(name = "siteCopyright")
    public String getSiteCopyRight() {
        return configService.getSiteConfig().getCopyright();
    }

}
