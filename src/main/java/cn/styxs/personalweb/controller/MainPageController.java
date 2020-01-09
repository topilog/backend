package cn.styxs.personalweb.controller;

import cn.styxs.personalweb.model.ArticleInfo;
import cn.styxs.personalweb.repository.ArticleInfoRepository;
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
        ArrayList<ArticleInfo> list = new ArrayList<>();
        articleInfoRepository.findAll().forEach(article -> list.add(article));
        model.addAttribute("articles", list);
        return "index";
    }
}
