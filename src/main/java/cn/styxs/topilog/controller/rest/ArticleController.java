package cn.styxs.topilog.controller.rest;

import cn.styxs.topilog.annotation.EnablePermission;
import cn.styxs.topilog.annotation.PermissionRequired;
import cn.styxs.topilog.controller.request.ArticlePostRequest;
import cn.styxs.topilog.controller.response.ArticlePageResp;
import cn.styxs.topilog.controller.response.BaseResponse;
import cn.styxs.topilog.model.article.ArticleInfo;
import cn.styxs.topilog.model.ErrorCode.Article;
import cn.styxs.topilog.service.ArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/article")
@EnablePermission
@Slf4j
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(path = "/infos", method = {RequestMethod.GET})
    @ApiOperation(value = "拉取所有文章信息（权限）", notes = "不包含文章内容信息")
    @PermissionRequired(description = "拉取所有文章权限")
    public BaseResponse<List<ArticleInfo>> listArticleInfos() {
        BaseResponse<List<ArticleInfo>> response = new BaseResponse<>();
        response.succeed(articleService.getArticleInfos());
        return response;
    }

    @RequestMapping(path = "/infos/page", method = {RequestMethod.GET})
    @ApiOperation(value = "按分页拉取文章信息列表", notes = "不包含文章内容信息")
    public BaseResponse<ArticlePageResp> listArticleInfosByPage(@RequestParam(value = "page") @Valid int pageIndex,
                                                                @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        BaseResponse<ArticlePageResp> response = new BaseResponse<>();
        ArticlePageResp resp = ArticlePageResp.builder().currentPage(pageIndex).pageSize(pageSize)
                .pageLength(articleService.getArticlePageCount(pageSize))
                .articleInfos(articleService.getArticleInfos(pageIndex, pageSize)).build();
        response.succeed(resp);
        return response;
    }

    @RequestMapping(path = "/info", method = {RequestMethod.GET})
    @ApiOperation(value = "获取某篇文章信息", notes = "不包含文章内容信息")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true)
    public BaseResponse<ArticleInfo> getArticleInfo(@RequestParam(value = "articleId", defaultValue = "-1") Long articleId) {
        BaseResponse<ArticleInfo> response = new BaseResponse<>();
        if (articleId == -1) {
            response.failed("do not have articleId", Article.kArticleIdError);
        }
        else {
            ArticleInfo articleInfo = articleService.getArticleInfo(articleId);
            if (articleInfo == null) {
                response.failed("can't find any article by this id", Article.kArticleIdError);
            } else {
                response.succeed(articleInfo);
            }
        }
        return response;
    }

    @RequestMapping(path = "/article", method = {RequestMethod.POST})
    @PermissionRequired(description = "提交文章权限")
    @ApiOperation(value = "提交新文章")
    public BaseResponse<ArticleInfo> postArticle(@RequestBody @Valid ArticlePostRequest postRequest) {
        ArticleInfo info = postRequest.make();
        info.setTags(articleService.convertTags(postRequest.getTags()));
        articleService.addArticleInfo(info);
        BaseResponse<ArticleInfo> BaseResponse = new BaseResponse<>();
        BaseResponse.succeed(info);
        return BaseResponse;
    }

    @RequestMapping(path = "/article", method = {RequestMethod.GET})
    @ApiOperation(value = "获取文章具体内容", notes = "返回为包含Content部分的ArticleInfo模型")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true)
    public BaseResponse<ArticleInfo> getArticleContent(@RequestParam(value = "articleId", defaultValue = "-1") Long articleId) {
        BaseResponse<ArticleInfo> response = new BaseResponse<>();
        if (articleId == -1)
            response.failed("do not have articleId", Article.kArticleIdError);
        else {
            ArticleInfo infoContent = articleService.getArticleContent(articleId);
            if (infoContent == null) {
                response.failed("can't find any content by this id", Article.kArticleIdError);
            } else {
                response.succeed(infoContent);
            }
        }
        return response;
    }

    @RequestMapping(path = "/article/top", method = {RequestMethod.POST})
    @ApiOperation(value = "设置文章置顶标志（权限）", notes = "设置置顶或取消置顶")
    @PermissionRequired(description = "置顶或取消置顶权限")
    public BaseResponse<Object> setArticleTop(@RequestParam(value = "articleId") @Valid Long articleId,
                                                  @RequestParam(value = "top") @Valid Boolean top) {
        BaseResponse response = new BaseResponse<>();
        if (articleService.setArticleTop(articleId, top)) {
            response.succeed(null);
        } else {
            response.failed("set top failed", 0);
        }
        return response;
    }

}


