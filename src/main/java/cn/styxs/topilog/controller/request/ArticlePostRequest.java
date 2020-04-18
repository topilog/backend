package cn.styxs.topilog.controller.request;

import cn.styxs.topilog.model.article.ArticleContent;
import cn.styxs.topilog.model.article.ArticleInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 提交文章接口所需的参数类
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ArticlePostRequest extends UserRequest {
    @ApiModelProperty(value = "文章标题", required = true)
    @NotNull
    @NotEmpty
    @Size(max = ArticleInfo.kArticleTitleLength)
    String title;
    @ApiModelProperty(value = "文章内容", required = true)
    @NotNull
    @NotEmpty
    String content;
    @ApiModelProperty(value = "文章展示摘要", required = false)
    @Size(max = ArticleInfo.kArticleSummaryLength)
    String summary;
    @ApiModelProperty(value = "文章分类数组", notes = "如果是目前不存在的分类将自动创建新分类")
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