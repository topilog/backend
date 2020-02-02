package cn.styxs.personalweb;

import cn.styxs.personalweb.model.ArticleContent;
import cn.styxs.personalweb.model.ArticleInfo;
import cn.styxs.personalweb.model.NavTabItem;
import cn.styxs.personalweb.repository.NavTabItemRepository;
import cn.styxs.personalweb.service.ArticleService;
import cn.styxs.personalweb.service.PermissionService;
import cn.styxs.personalweb.service.RoleService;
import cn.styxs.personalweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataInitRunner implements ApplicationRunner {
    @Autowired
    ArticleService articleService;
    @Autowired
    NavTabItemRepository navTabItemRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initTestArticle();
        initNavTabItem();
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

    // 创建初始Tab列表
    private void initNavTabItem() {
        if (navTabItemRepository.count() == 0) {
            navTabItemRepository.save(NavTabItem.builder().title("首页").navigateToUrl("/").build());
            navTabItemRepository.save(NavTabItem.builder().title("栏目").navigateToUrl("/tags").build());
            navTabItemRepository.save(NavTabItem.builder().title("关于").navigateToUrl("/about").build());
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
        userService.createUser("adminUser", "12345", "0");
        roleService.createRole("superAdmin");
        roleService.addPermissions("superAdmin", permissionService.listPermissions());
        roleService.addRolesToUser("adminUser", "superAdmin");
    }
}
