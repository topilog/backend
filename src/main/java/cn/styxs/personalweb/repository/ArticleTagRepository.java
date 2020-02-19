package cn.styxs.personalweb.repository;

import cn.styxs.personalweb.model.ArticleTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description:
 */
public interface ArticleTagRepository extends CrudRepository<ArticleTag, Long> {
    public List<ArticleTag> findAllByOrderById();

    public ArticleTag findByName(String name);
}
