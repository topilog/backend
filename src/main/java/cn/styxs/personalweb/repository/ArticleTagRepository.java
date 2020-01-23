package cn.styxs.personalweb.repository;

import cn.styxs.personalweb.model.ArticleTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleTagRepository extends CrudRepository<ArticleTag, Long> {
    public List<ArticleTag> findAllByOrderById();

    public ArticleTag findByName(String name);
}
