package cn.styxs.personalweb.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "article_content")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleContent extends BaseModel implements Serializable {
    @Column(name = "content")
    private String content;
}
