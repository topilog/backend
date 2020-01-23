package cn.styxs.personalweb.controller.request;

import cn.styxs.personalweb.model.ArticleContent;
import cn.styxs.personalweb.model.ArticleInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class ArticlePostRequest extends UserRequest {
    @NotNull
    String title;
    @NotNull
    String content;
    String summary;
    List<String> tags;

    public ArticleInfo make() {
        if (summary == null) {
            // 从文章首部截出一部分作为摘要，最好寻找到换行符为界限
            int limit = content.length() > ArticleInfo.kArticleSummaryLength ? ArticleInfo.kArticleSummaryLength : content.length();
            StringBuilder sb = new StringBuilder(content.substring(0, limit));
            int end = sb.lastIndexOf("\n");
            if (end > limit / 2) {
                sb.subSequence(0, end);
            }
            summary = sb.toString();
        }
        // tags需要单独处理
        return ArticleInfo.builder().title(title).summary(summary).articleModifiedTime(new Date())
                .artContent(new ArticleContent(content)).build();
    }


}