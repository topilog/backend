package cn.styxs.personalweb.controller;

import cn.styxs.personalweb.model.ArticleInfo;
import cn.styxs.personalweb.repository.ArticleInfoRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainPageController {
    @Autowired
    private ArticleInfoRepository articleInfoRepository;

    @GetMapping("/")
    public String handleMainPage(Model model) {
        ArrayList<ArticleInfo> articles = new ArrayList<>();
        articleInfoRepository.findAll().forEach(article -> articles.add(article));
        model.addAttribute("articles", articles);
        
        return "index";
    }
}
