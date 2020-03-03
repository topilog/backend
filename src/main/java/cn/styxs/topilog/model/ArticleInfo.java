package cn.styxs.topilog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 存储文章信息(不包括文章具体内容)
 */
@Entity
@Table(name = "article_info")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleInfo extends BaseModel implements Serializable {
    public static final int kArticleTitleLength = 80;
    public static final int kArticleSummaryLength = 600;


    @Column(name = "title", length = kArticleTitleLength)
    private String title;
    @Column(name = "summary", length = kArticleSummaryLength)
    private String summary;
    @Column(name = "articleModified")
    private Date articleModifiedTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_content_id")
    @JsonIgnore
    private ArticleContent artContent;
    @ManyToMany
    @JoinTable(name = "article_article_tag")
    private List<ArticleTag> tags;
}
