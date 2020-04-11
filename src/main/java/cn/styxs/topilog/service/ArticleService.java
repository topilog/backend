package cn.styxs.topilog.service;

import cn.styxs.topilog.model.OffsetPage;
import cn.styxs.topilog.model.article.ArticleInfo;
import cn.styxs.topilog.model.article.ArticleTag;
import cn.styxs.topilog.model.article.TopArticle;
import cn.styxs.topilog.repository.ArticleInfoRepository;
import cn.styxs.topilog.repository.ArticleTagRepository;
import cn.styxs.topilog.repository.TopArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 提供Article相关功能
 */
@Service
@Transactional
@Slf4j
public class ArticleService {
    @Autowired
    ArticleInfoRepository infoRepository;
    @Autowired
    ArticleTagRepository tagRepository;
    @Autowired
    TopArticleRepository topArticleRepository;


    public long getArticleNums() {
        return infoRepository.count();
    }

    public List<ArticleInfo> getArticleInfos() {
        return infoRepository.findAllByOrderByCreateTime();
    }

    // 分页查询文章信息列表
    public List<ArticleInfo> getArticleInfos(Integer currentPage, Integer pageSize) {
        // 置顶逻辑
        List<Long> topArticleIds = topArticleRepository.queryTopArticleAIds();
        ArrayList<ArticleInfo> result = new ArrayList();
        int topNums = topArticleIds.size();
        int l = currentPage * pageSize;
        int r = l + pageSize;
        if (topNums == 0) {
            // 当topArticleIds为空时拿进repository查询会有问题
            result.addAll(infoRepository.findAllBy(new OffsetPage(l, pageSize)).getContent());
        } else if (topNums < l) {
            // 当前页完全无置顶文章
            int offset = l - topNums;
            Page<ArticleInfo> page = infoRepository.findAllByIdNotIn(topArticleIds, new OffsetPage(offset, pageSize));
            result.addAll(page
                    .getContent());
        } else if (topNums >= l && topNums < r) {
            // 当前页有部分置顶文章
            result.addAll(infoRepository.findAllByIdIsIn(topArticleIds.subList(l, topNums)));
            result.addAll(infoRepository.findAllByIdNotIn(topArticleIds, new OffsetPage(0, r - topNums))
                    .getContent());
        } else {
            // 当前页全是置顶文章
            result.addAll(infoRepository.findAllByIdIsIn(topArticleIds.subList(l, r)));
        }
        return result;
    }

    public int getArticlePageCount(Integer pageSize) {
        return  (int) Math.ceil(((double)infoRepository.count()) / pageSize);
    }

    // 设置某篇文章的置顶标记
    public boolean setArticleTop(Long aid, boolean top) {
        ArticleInfo articleInfo = infoRepository.findById(aid).get();
        if (articleInfo == null) {
            return false;
        }
        if (top) {
            if (topArticleRepository.existsByArticleInfo(articleInfo)) {
                return false;
            }
            TopArticle topArticle = new TopArticle(articleInfo);
            topArticleRepository.save(topArticle);
        } else {
            TopArticle topArticle = topArticleRepository.findByArticleInfo(articleInfo);
            if (topArticle == null) {
                return false;
            }
            topArticleRepository.delete(topArticle);
        }
        return true;
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

    public ArticleInfo getArticleContent(Long id) {
        Optional<ArticleInfo> info = infoRepository.findById(id);
        if (info.isPresent()) {
            ArticleInfo infoContent = info.get();
            infoContent.getArtContent().getModifiedTime(); // 触发懒加载
            return infoContent;
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
