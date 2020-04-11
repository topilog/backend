package cn.styxs.topilog.repository;

import cn.styxs.topilog.model.article.ArticleInfo;
import cn.styxs.topilog.model.article.TopArticle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/11
 * @Description: TopArticle的Repository
 */
public interface TopArticleRepository extends CrudRepository<TopArticle, Long> {

    @Query("select top.articleInfo.id from TopArticle top order by top.createTime desc")
    List<Long> queryTopArticleAIds();

    TopArticle findByArticleInfo(ArticleInfo info);

    boolean existsByArticleInfo(ArticleInfo articleInfo);
}
