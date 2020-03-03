package cn.styxs.topilog.service;

import cn.styxs.topilog.model.ArticleInfo;
import cn.styxs.topilog.model.ArticleTag;
import cn.styxs.topilog.repository.ArticleInfoRepository;
import cn.styxs.topilog.repository.ArticleTagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 提供Article相关功能
 */
@Service
@Slf4j
public class ArticleService {
    @Autowired
    ArticleInfoRepository infoRepository;
    @Autowired
    ArticleTagRepository tagRepository;


    public long getArticleNums() {
        return infoRepository.count();
    }

    public List<ArticleInfo> getArticleInfos() {
        return infoRepository.findAllByOrderByCreateTime();
    }

    public void addArticleInfo(ArticleInfo articleInfo) {
        infoRepository.save(articleInfo);
    }

    public void addArticleInfos(List<ArticleInfo> articleInfos) {
        infoRepository.saveAll(articleInfos);
    }

    public ArticleInfo getArticleInfo(Long id) {
        Optional<ArticleInfo> info = infoRepository.findById(id);
        if (info.isPresent()) {
            return info.get();
        }
        return null;
    }

    public List<ArticleTag> getArticleTags() {
        return tagRepository.findAllByOrderById();
    }

    // 从String转换为有效标签
    public List<ArticleTag> convertTags(List<String> strings) {
        if (strings == null)
            return null;
        ArrayList<ArticleTag> tags = new ArrayList<>();
        for (String s : strings) {
            ArticleTag tag = tagRepository.findByName(s);
            // 标签不存在则插入
            if (tag == null) {
                tag = ArticleTag.builder().name(s).build();
                tagRepository.save(tag);
            }
            tags.add(tag);
        }
        return tags;
    }
}
