package cn.styxs.topilog.controller.rest;

import cn.styxs.topilog.controller.response.BaseResponse;
import cn.styxs.topilog.model.FollowSite;
import cn.styxs.topilog.service.FollowSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/28
 * @Description:
 */
@RestController
@RequestMapping("/follow")
public class FollowSiteController {
    @Autowired
    FollowSiteService followSiteService;

    @GetMapping("/list")
    public BaseResponse<List<FollowSite>> getFollowList() {
        BaseResponse<List<FollowSite>> response = new BaseResponse<>();
        response.succeed(followSiteService.listByOrder());
        return response;
    }

    @PostMapping("/add")
    public BaseResponse<List<FollowSite>> addFollowSite(@RequestBody @Valid FollowSite followSite) {
        BaseResponse<List<FollowSite>> response = new BaseResponse<>();
        if (followSiteService.insert(followSite) != null) {
            response.succeed(followSiteService.listByOrder());
        } else {
            response.failed("fail to insert followSite", 1);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public BaseResponse delFollowSite(@PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        followSiteService.remove(id);
        response.succeed(null);
        return response;
    }
}
