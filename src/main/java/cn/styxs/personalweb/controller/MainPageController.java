package cn.styxs.personalweb.controller;

import cn.styxs.personalweb.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainPageController {
    @GetMapping("/")
    public String handleMainPage(Model model) {
        ArrayList<Article> list = new ArrayList<>();
        list.add(new Article("文章1", "内容说明1"));
        list.add(new Article("文章2", "内容说明2"));
        model.addAttribute("articles",list);
        return "index";
    }
}
