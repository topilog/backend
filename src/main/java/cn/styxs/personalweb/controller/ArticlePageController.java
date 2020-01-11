package cn.styxs.personalweb.controller;

import cn.styxs.personalweb.model.ArticleInfo;
import cn.styxs.personalweb.repository.ArticleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticlePageController {
    @Autowired
    ArticleInfoRepository articleInfoRepository;

    @GetMapping("/article/{articleId}")
    public String handleArticlePage(@PathVariable("articleId") Long aid, Model model) {
        ArticleInfo info = articleInfoRepository.findById(aid).get();
        if (info != null) {
            model.addAttribute("article_title", info.getTitle());
            model.addAttribute("article_content", info.getArtContent().getContent());
            return "article";
        }
        return null;
    }
}
