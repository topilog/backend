package cn.styxs.topilog.model.article;

import cn.styxs.topilog.model.BaseModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 文章分类标签
 */
@Entity
@Table(name = "article_tag")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTag extends BaseModel implements Serializable {
    private String name;
}
