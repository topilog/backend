package cn.styxs.personalweb.controller;

import cn.styxs.personalweb.model.ArticleInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainPageController {
    @GetMapping("/")
    public String handleMainPage(Model model) {
        ArrayList<ArticleInfo> list = new ArrayList<>();
        list.add(ArticleInfo.builder().title("文章1").summary("内容说明1").build());
        list.add(ArticleInfo.builder().title("文章2").summary("内容说明2").build());
        model.addAttribute("articles", list);
        return "index";
    }
}
