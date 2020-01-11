package cn.styxs.personalweb.controller;

import cn.styxs.personalweb.model.NavTabItem;
import cn.styxs.personalweb.repository.NavTabItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class SkeletonController {
    @Autowired
    NavTabItemRepository navTabItemRepository;

    @ModelAttribute(name = "navTabItems")
    public List<NavTabItem> getNavTabItems() {
        ArrayList<NavTabItem> tabItems = new ArrayList<>();
        navTabItemRepository.findAll().forEach(item -> tabItems.add(item));
        return tabItems;
    }
}
