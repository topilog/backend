package cn.styxs.topilog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 保存站点信息 （这些信息将向前端下发）
 */
@Getter
@ToString
public class SiteInfo {
    @Value("${site.name}")
    private String siteName;

    @JsonIgnore
    @Value("${site.nav.tabs}")
    private List<String> navTabs;

    @JsonIgnore
    @Value("${site.nav.urls}")
    private List<String> navUrls;

    // 由上面的navTabs和navUrls组成
    private List<NavTabItem> navItems;
    public void setNavItems(List<NavTabItem> navItems) {
        this.navItems = navItems;
    }

    @Value("${site.copyright}")
    private String copyright;

}
