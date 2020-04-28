package cn.styxs.topilog.service;

import cn.styxs.topilog.model.FollowSite;
import cn.styxs.topilog.repository.FollowSiteRepository;
import org.springframework.stereotype.Service;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/16
 * @Description: 容纳关注站点的相关逻辑
 */
@Service
public class FollowSiteService extends DisplayOrderService<FollowSite, FollowSiteRepository> {
}
