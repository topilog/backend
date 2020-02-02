package cn.styxs.personalweb.controller.rest;

import cn.styxs.personalweb.annotation.EnablePermission;
import cn.styxs.personalweb.annotation.LoginRequired;
import cn.styxs.personalweb.annotation.PermissionRequired;
import cn.styxs.personalweb.controller.request.ArticlePostRequest;
import cn.styxs.personalweb.controller.response.ContentResponse;
import cn.styxs.personalweb.model.ArticleContent;
import cn.styxs.personalweb.model.ArticleInfo;
import cn.styxs.personalweb.service.ArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@EnablePermission
@Slf4j
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(path = "/infos", method = {RequestMethod.GET})
    @ApiOperation(value = "拉取所有文章信息", notes = "不包含文章内容信息")
    public ContentResponse listArticleInfos() {
        ContentResponse<List<ArticleInfo>> response = new ContentResponse<>();
        response.setContent(articleService.getArticleInfos());
        response.succeed();
        return response;
    }

    @RequestMapping(path = "/info", method = {RequestMethod.GET})
    @ApiOperation(value = "获取某篇文章信息", notes = "不包含文章内容信息")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true)
    public ContentResponse getArticleInfo(@RequestParam(value = "articleId", defaultValue = "-1") Long articleId) {
        ContentResponse<ArticleInfo> response = new ContentResponse<>();
        if (articleId == -1)
            response.failed("do not have articleId", 1);
        else {
            response.setContent(articleService.getArticleInfo(articleId));
            response.succeed();
        }
        return response;
    }

    @RequestMapping(path = "/article", method = {RequestMethod.POST})
    @PermissionRequired(description = "提交文章权限")
    @ApiOperation(value = "提交新文章")
    public ContentResponse postArticle(@RequestBody ArticlePostRequest postRequest) {
        ArticleInfo info = postRequest.make();
        info.setTags(articleService.convertTags(postRequest.getTags()));
        articleService.addArticleInfo(info);
        ContentResponse<ArticleInfo> contentResponse = new ContentResponse<>();
        contentResponse.setContent(info);
        contentResponse.succeed();
        return contentResponse;
    }

    @RequestMapping(path = "/article", method = {RequestMethod.GET})
    @ApiOperation(value = "获取文章具体内容", notes = "Markdown格式内容")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true)
    public ContentResponse getArticleContent(@RequestParam(value = "articleId", defaultValue = "-1") Long articleId) {
        ContentResponse<ArticleContent> response = new ContentResponse<>();
        if (articleId == -1)
            response.failed("do not have articleId", 1);
        else {
            response.setContent(articleService.getArticleInfo(articleId).getArtContent());
            response.succeed();
        }
        return response;
    }

}


