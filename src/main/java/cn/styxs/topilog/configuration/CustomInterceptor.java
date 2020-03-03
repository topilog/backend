package cn.styxs.topilog.configuration;


import cn.styxs.topilog.controller.response.BaseResponse;
import cn.styxs.topilog.service.URIAccessService;
import cn.styxs.topilog.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: styxs
 * @CreateTime: 2020/2/22
 * @Description: 提供一些Interceptor可能用到的基础方法
 */
public class CustomInterceptor implements HandlerInterceptor {
    protected URIAccessService uriAccessService;
    protected UserService userService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public CustomInterceptor(URIAccessService uriAccessService, UserService userService) {
        this.uriAccessService = uriAccessService;
        this.userService = userService;
    }

    /*
        获取Request中用户是否已登录
        成功返回用户名，未登录返回null
     */
    protected String getUsernameFromRequest(HttpServletRequest request) {
        String token = (String)request.getSession().getAttribute(UserService.kTokenAttributeName);
        return userService.verifyLogin(token);
    }

    /*
        是否拦截打印日志
        true表放通， false表拦截
     */
    protected void log(boolean allow) {
        log(allow, "");
    }
    /*
        拦截信息打印日志
        true表放通， false表拦截
     */
    protected void log(boolean allow, String message) {
        log.info(allow + " | " + message);
    }

    /*
        向请求回写相应信息
     */
    protected void out(HttpServletResponse response, String description, int code) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.throwout(description, code);
            mapper.writeValue(response.getWriter(), baseResponse);
        }
        catch (IOException e) {
            log.error(e.toString());
        }
    }

}
