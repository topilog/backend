package cn.styxs.personalweb.controller.rest;

import cn.styxs.personalweb.controller.request.ArticlePostRequest;
import cn.styxs.personalweb.model.ArticleContent;
import cn.styxs.personalweb.model.ArticleInfo;
import cn.styxs.personalweb.service.ArticleService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(path = "/infos", method = {RequestMethod.GET})
    public List<ArticleInfo> listArticleInfos() {
        return articleService.getArticleInfos();
    }

    @RequestMapping(path = "/info", method = {RequestMethod.GET})
    public ArticleInfo getArticleInfo(@RequestParam(value = "articleId", defaultValue = "-1") Long articleId) {
        if (articleId == -1)
            return null;
        else {
            return articleService.getArticleInfo(articleId);
        }
    }

    @RequestMapping(path = "/article", method = {RequestMethod.POST})
    public ArticleInfo postArticle(@RequestBody ArticlePostRequest postRequest) {
        ArticleInfo info = postRequest.make();
        info.setTags(articleService.convertTags(postRequest.getTags()));
        articleService.addArticleInfo(info);
        return info;
    }

    @RequestMapping(path = "/content", method = {RequestMethod.GET})
    public ArticleContent getArticleContent(@RequestParam(value = "articleId", defaultValue = "-1") Long articleId) {
        if (articleId == -1)
            return null;
        else {
            return articleService.getArticleInfo(articleId).getArtContent();
        }
    }

}


