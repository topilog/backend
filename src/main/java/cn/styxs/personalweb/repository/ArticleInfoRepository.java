package cn.styxs.personalweb.repository;

import cn.styxs.personalweb.model.ArticleInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleInfoRepository extends CrudRepository<ArticleInfo, Long> {
    List<ArticleInfo> findAllByOrderByCreateTime();
}
