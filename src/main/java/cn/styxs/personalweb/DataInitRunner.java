package cn.styxs.personalweb;

import cn.styxs.personalweb.model.ArticleInfo;
import cn.styxs.personalweb.repository.ArticleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataInitRunner implements ApplicationRunner {
    @Autowired
    ArticleInfoRepository articleInfoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ArrayList<ArticleInfo> list = new ArrayList<>();
        list.add(ArticleInfo.builder().title("文章1").summary("内容说明1").build());
        list.add(ArticleInfo.builder().title("文章2").summary("内容说明2").build());
        articleInfoRepository.saveAll(list);
    }
}
