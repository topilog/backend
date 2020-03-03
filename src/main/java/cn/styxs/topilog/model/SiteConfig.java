package cn.styxs.topilog.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 保存站点设置
 */
@Getter
@ToString
public class SiteConfig {
    @Value("${site.name}")
    private String siteName;

    @Value("${site.nav.tabs}")
    private List<String> navTabs;

    @Value("${site.nav.urls}")
    private List<String> navUrls;

    @Value("${site.copyright}")
    private String copyright;
}
