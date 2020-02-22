package cn.styxs.personalweb.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @Author: styxs
 * @CreateTime: 2020/2/21
 * @Description: URIAccessService测试
 */
@SpringBootTest
class URIAccessServiceTest {
    @Autowired
    URIAccessService uriAccessService;

    @Test
    public void testLogin() {
        String message = "Test uri need Login error";
        uriAccessService.addRuleForLogin("/user/profile");
        uriAccessService.addRuleForLogin("/needLogin/*");

        Assert.isTrue(!uriAccessService.canAccessWithoutLogin("/user/profile"), message);
        Assert.isTrue(!uriAccessService.canAccessWithoutLogin("/user/profile/"), message);
        Assert.isTrue(uriAccessService.canAccessWithoutLogin("/user/login"), message);
        Assert.isTrue(!uriAccessService.canAccessWithoutLogin("/needLogin/aaa"), message);
        Assert.isTrue(!uriAccessService.canAccessWithoutLogin("/needLogin/aaa/bbb"), message);
        Assert.isTrue(uriAccessService.canAccessWithoutLogin("/url/"), message);
    }

    @Test
    public void testPermission() {
        String message = "Test uri need Permission Error";
        uriAccessService.addRuleForPermission("/admin/*", "SuperAdmin");
        Assert.isTrue(!uriAccessService.canAccessWithoutPermission("/admin/a"), message);
        Assert.isTrue("SuperAdmin".equals(uriAccessService.getPermissionWithURI("/admin/a")), message);
    }
}