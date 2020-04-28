package cn.styxs.topilog.service;

import cn.styxs.topilog.model.FollowSite;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/16
 * @Description:
 */
@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FollowSiteServiceTest {
    @Autowired
    FollowSiteService service;

    int itemNums = 5;

    @Test
    @Order(1)
    void followNewSite() {
        Assert.isTrue(service.followNewSite("翔兽1", "blog.styxs.cn", "翔兽写字的地方"), "1插入问题");
        Assert.isTrue(service.followNewSite("翔兽2", "aa", "bb"), "2插入问题");
        Assert.isTrue(service.followNewSite("翔兽3", "aa", "bb"), "3插入问题");
        Assert.isTrue(service.followNewSite("翔兽4", "aa", "bb"), "4插入问题");
        Assert.isTrue(service.followNewSite("翔兽5", "aa", "bb"), "5插入问题");
        // itemNums
        Assert.isTrue(!service.followNewSite("", "aa", "bb"), "名称不能为空串");
        Assert.isTrue(!service.followNewSite("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aa", "bb"), "长度限制异常");
    }

    @Test
    @Order(2)
    void getFollowSites() {
        log.info(""+service.getFollowSites().size());
        log.info(service.getFollowSites().toString());
        log.info(""+itemNums);
        Assert.isTrue( service.getFollowSites().size() == itemNums, "插入后查询异常");

    }

    @Test
    @Order(3)
    void movFollowSite() {
        List<FollowSite> followSites = service.getFollowSites();
        log.info(followSites.toString());
        service.changeOrder(followSites.get(followSites.size() - 1), 1);
        List<FollowSite> newFollowSites = service.getFollowSites();

        log.info(newFollowSites.toString());
        Assert.isTrue(
                newFollowSites.get(1).equals(followSites.get(followSites.size() - 1)) &&
                        followSites.get(followSites.size() - 2).equals(newFollowSites.get(newFollowSites.size()-1))
                , "移动顺序异常");
    }

    @Test
    @Order(4)
    void delFollowSite() {
        List<FollowSite> followSites = service.getFollowSites();
        int num = followSites.size();
        service.delFollowSite(followSites.get(0).getId());
        Assert.isTrue(service.getFollowSites().size() != num -1, "删除异常");
    }


}