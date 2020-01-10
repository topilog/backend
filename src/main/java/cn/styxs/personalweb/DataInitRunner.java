package cn.styxs.personalweb;

import cn.styxs.personalweb.model.ArticleInfo;
import cn.styxs.personalweb.model.NavTabItem;
import cn.styxs.personalweb.repository.ArticleInfoRepository;
import cn.styxs.personalweb.repository.NavTabItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataInitRunner implements ApplicationRunner {
    @Autowired
    ArticleInfoRepository articleInfoRepository;
    @Autowired
    NavTabItemRepository navTabItemRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initTestArticle();
        initNavTabItem();
    }

    private void initTestArticle() {
        ArrayList<ArticleInfo> list = new ArrayList<>();
        list.add(ArticleInfo.builder().title("文章1").summary("内容说明1").build());
        list.add(ArticleInfo.builder().title("文章2").summary("内容说明2").build());
        articleInfoRepository.saveAll(list);
    }

    private void initNavTabItem() {
        if (navTabItemRepository.count() == 0) {
            navTabItemRepository.save(NavTabItem.builder().title("首页").navigateToUrl("/").build());
            navTabItemRepository.save(NavTabItem.builder().title("栏目").navigateToUrl("/tags").build());
            navTabItemRepository.save(NavTabItem.builder().title("关于").navigateToUrl("/about").build());
        }
    }
}
