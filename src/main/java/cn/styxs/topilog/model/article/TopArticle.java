package cn.styxs.topilog.model.article;

import cn.styxs.topilog.model.BaseModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/11
 * @Description: 用于存储置顶文章
 */
@Entity
@Table(name = "article_top")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopArticle extends BaseModel {
    @OneToOne
    @JoinColumn(name = "article_id")
    private ArticleInfo articleInfo;
}
