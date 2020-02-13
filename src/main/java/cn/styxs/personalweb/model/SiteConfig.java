package cn.styxs.personalweb.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
