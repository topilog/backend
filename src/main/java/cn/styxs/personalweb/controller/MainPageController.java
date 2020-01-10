package cn.styxs.personalweb.controller;

import cn.styxs.personalweb.model.ArticleInfo;
import cn.styxs.personalweb.model.NavTabItem;
import cn.styxs.personalweb.repository.ArticleInfoRepository;
import cn.styxs.personalweb.repository.NavTabItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainPageController {
    @Autowired
    private ArticleInfoRepository articleInfoRepository;
    @Autowired
    private NavTabItemRepository navTabItemRepository;

    @GetMapping("/")
    public String handleMainPage(Model model) {
        ArrayList<ArticleInfo> articles = new ArrayList<>();
        articleInfoRepository.findAll().forEach(article -> articles.add(article));
        model.addAttribute("articles", articles);

        ArrayList<NavTabItem> tabItems = new ArrayList<>();
        navTabItemRepository.findAll().forEach(item -> tabItems.add(item));
        model.addAttribute("navTabItems", tabItems);
        return "index";
    }
}
