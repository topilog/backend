package cn.styxs.topilog.controller.rest;

import cn.styxs.topilog.annotation.EnablePermission;
import cn.styxs.topilog.annotation.PermissionRequired;
import cn.styxs.topilog.controller.request.ArticlePostRequest;
import cn.styxs.topilog.controller.response.BaseResponse;
import cn.styxs.topilog.model.ArticleContent;
import cn.styxs.topilog.model.ArticleInfo;
import cn.styxs.topilog.service.ArticleService;
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
    public BaseResponse<List<ArticleInfo>> listArticleInfos() {
        BaseResponse<List<ArticleInfo>> response = new BaseResponse<>();
        response.succeed(articleService.getArticleInfos());
        return response;
    }

    @RequestMapping(path = "/info", method = {RequestMethod.GET})
    @ApiOperation(value = "获取某篇文章信息", notes = "不包含文章内容信息")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true)
    public BaseResponse getArticleInfo(@RequestParam(value = "articleId", defaultValue = "-1") Long articleId) {
        BaseResponse<ArticleInfo> response = new BaseResponse<>();
        if (articleId == -1)
            response.failed("do not have articleId", 1);
        else {
            response.succeed(articleService.getArticleInfo(articleId));
        }
        return response;
    }

    @RequestMapping(path = "/article", method = {RequestMethod.POST})
    @PermissionRequired(description = "提交文章权限")
    @ApiOperation(value = "提交新文章")
    public BaseResponse postArticle(@RequestBody ArticlePostRequest postRequest) {
        ArticleInfo info = postRequest.make();
        info.setTags(articleService.convertTags(postRequest.getTags()));
        articleService.addArticleInfo(info);
        BaseResponse<ArticleInfo> BaseResponse = new BaseResponse<>();
        BaseResponse.succeed(info);
        return BaseResponse;
    }

    @RequestMapping(path = "/article", method = {RequestMethod.GET})
    @ApiOperation(value = "获取文章具体内容", notes = "Markdown格式内容")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true)
    public BaseResponse getArticleContent(@RequestParam(value = "articleId", defaultValue = "-1") Long articleId) {
        BaseResponse<ArticleContent> response = new BaseResponse<>();
        if (articleId == -1)
            response.failed("do not have articleId", 1);
        else {
            response.succeed(articleService.getArticleInfo(articleId).getArtContent());
        }
        return response;
    }

}

