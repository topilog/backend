package cn.styxs.personalweb.configuration;

import cn.styxs.personalweb.service.URIAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Author: styxs
 * @CreateTime: 2020/2/22
 * @Description:
 */
@Configuration
public class URIRuleConfigurer {
    @Autowired
    URIAccessService uriAccessService;

    @PostConstruct
    public void initURIRules() {
        // ------ 添加登录URI规则------


        // ------ 添加权限URI规则------
        uriAccessService.addRuleForPermission("/admin.html", "admin");
    }
}
