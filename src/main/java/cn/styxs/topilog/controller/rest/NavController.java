package cn.styxs.topilog.controller.rest;

import cn.styxs.topilog.controller.response.BaseResponse;
import cn.styxs.topilog.model.NavTabItem;
import cn.styxs.topilog.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class NavController {
    @Autowired
    NavService navService;

    @RequestMapping(value = "/nav", method = {RequestMethod.GET})
    public BaseResponse<List<NavTabItem>> getNavList() {
        BaseResponse<List<NavTabItem>> response = new BaseResponse<>();
        response.succeed(navService.getTabItems());
        return response;
    }
}
