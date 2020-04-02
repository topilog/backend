package cn.styxs.topilog.controller.rest;

import cn.styxs.topilog.controller.response.BaseResponse;
import cn.styxs.topilog.model.SiteInfo;
import cn.styxs.topilog.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SiteInfoController {
    @Autowired
    ConfigService configService;

    @RequestMapping(value = "/site/info", method = {RequestMethod.GET})
    public BaseResponse<SiteInfo> getSiteInfo() {
        BaseResponse<SiteInfo> response = new BaseResponse<>();
        response.succeed(configService.getSiteInfo());
        return response;
    }
}
