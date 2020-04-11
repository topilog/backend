package cn.styxs.topilog.repository;

import cn.styxs.topilog.model.article.ArticleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description:
 */
public interface ArticleInfoRepository extends CrudRepository<ArticleInfo, Long> {
    List<ArticleInfo> findAllByOrderByCreateTime();

    Page<ArticleInfo> findAllBy(Pageable pageable);

    Page<ArticleInfo> findAllByIdNotIn(List<Long> ids,Pageable pageable);

    List<ArticleInfo> findAllByIdIsIn(List<Long> ids);
}
