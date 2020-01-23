package cn.styxs.personalweb;

import cn.styxs.personalweb.model.ArticleContent;
import cn.styxs.personalweb.model.ArticleInfo;
import cn.styxs.personalweb.model.NavTabItem;
import cn.styxs.personalweb.repository.NavTabItemRepository;
import cn.styxs.personalweb.service.ArticleService;
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
    }

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

    private void initNavTabItem() {
        if (navTabItemRepository.count() == 0) {
            navTabItemRepository.save(NavTabItem.builder().title("首页").navigateToUrl("/").build());
            navTabItemRepository.save(NavTabItem.builder().title("栏目").navigateToUrl("/tags").build());
            navTabItemRepository.save(NavTabItem.builder().title("关于").navigateToUrl("/about").build());
        }
    }
}
