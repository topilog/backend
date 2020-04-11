package cn.styxs.topilog.controller.response;

import cn.styxs.topilog.model.article.ArticleInfo;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/11
 * @Description: 容纳分页查询文章信息的结果
 */
@Builder
@Data
@ToString
public class ArticlePageResp {
    int currentPage;
    int pageSize;
    int pageLength;
    List<ArticleInfo> articleInfos;
}
