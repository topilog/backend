package cn.styxs.topilog.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 存储文章具体内容
 */
@Entity
@Table(name = "article_content")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleContent extends BaseModel implements Serializable {
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
}
