package cn.styxs.personalweb.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article_info")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleInfo extends BaseModel implements Serializable {
    @Column(name = "title")
    private String title;
    @Column(name = "summary")
    private String summary;
    @Column(name = "articleModified")
    private Date articleModifiedTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_content_id")
    private ArticleContent artContent;
    @ManyToMany
    @JoinTable(name = "article_article_tag")
    private List<ArticleTag> tags;
}
