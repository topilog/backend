package cn.styxs.topilog;

import cn.styxs.topilog.model.ArticleContent;
import cn.styxs.topilog.model.ArticleInfo;
import cn.styxs.topilog.service.ArticleService;
import cn.styxs.topilog.service.PermissionService;
import cn.styxs.topilog.service.RoleService;
import cn.styxs.topilog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 一些基础数据的插入在这里进行，后续可能考虑移除
 */
@Component
public class DataInitRunner implements ApplicationRunner {
    @Autowired
    ArticleService articleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initTestArticle();
        initRBAC();
    }

    // 创建测试文章
    private void initTestArticle() {
        if (articleService.getArticleNums() == 0) {
            ArrayList<ArticleInfo> list = new ArrayList<>();
            list.add(ArticleInfo.builder().title("文章1").summary("内容说明1")
                    .artContent(ArticleContent.builder().content("# Hello Markdown!").build()).build());
            list.add(ArticleInfo.builder().title("文章2").summary("内容说明2")
                    .artContent(ArticleContent.builder().content("| 行1  | 行2  |\n| ---- | ---- |\n| 1    | 2    |").build()).build());
            articleService.addArticleInfos(list);
        }
    }

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;
    // 创建测试用户-权限-权限组
    private void initRBAC() {
        userService.createUser("adminUser", "7092bff34d3d18e83dad9c4c7e78ff19", "V-w25dm9tDjF2pNyBiN7_F0pc3om5N4C");
        roleService.createRole("superAdmin");
        roleService.addPermissions("superAdmin", permissionService.listPermissions());
        roleService.addRolesToUser("adminUser", "superAdmin");
    }
}
