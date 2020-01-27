package cn.styxs.personalweb.controller.rest;

import cn.styxs.personalweb.annotation.LoginRequired;
import cn.styxs.personalweb.controller.request.ArticlePostRequest;
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
@Slf4j
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(path = "/infos", method = {RequestMethod.GET})
    @ApiOperation(value = "拉取所有文章信息", notes = "不包含文章内容信息")
    public List<ArticleInfo> listArticleInfos() {
        return articleService.getArticleInfos();
    }

    @RequestMapping(path = "/info", method = {RequestMethod.GET})
    @ApiOperation(value = "获取某篇文章信息", notes = "不包含文章内容信息")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true)
    public ArticleInfo getArticleInfo(@RequestParam(value = "articleId", defaultValue = "-1") Long articleId) {
        if (articleId == -1)
            return null;
        else {
            return articleService.getArticleInfo(articleId);
        }
    }

    @RequestMapping(path = "/article", method = {RequestMethod.POST})
    @LoginRequired
    @ApiOperation(value = "提交新文章")
    public ArticleInfo postArticle(@RequestBody ArticlePostRequest postRequest) {
        ArticleInfo info = postRequest.make();
        info.setTags(articleService.convertTags(postRequest.getTags()));
        articleService.addArticleInfo(info);
        return info;
    }

    @RequestMapping(path = "/article", method = {RequestMethod.GET})
    @ApiOperation(value = "获取文章具体内容", notes = "Markdown格式内容")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true)
    public ArticleContent getArticleContent(@RequestParam(value = "articleId", defaultValue = "-1") Long articleId) {
        if (articleId == -1)
            return null;
        else {
            return articleService.getArticleInfo(articleId).getArtContent();
        }
    }

}


