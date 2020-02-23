package cn.styxs.personalweb.configuration;

import cn.styxs.personalweb.annotation.PermissionRequired;
import cn.styxs.personalweb.controller.response.BaseResponse;
import cn.styxs.personalweb.model.Permission;
import cn.styxs.personalweb.service.PermissionService;
import cn.styxs.personalweb.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 权限拦截器，拦截访问带PermissionRequired注解的接口但不具有对应权限的请求
 */
@Slf4j
public class PermissionControlInterceptor implements HandlerInterceptor {
    PermissionService permissionService;
    UserService userService;

    public PermissionControlInterceptor(UserService userService, PermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            PermissionRequired permissionRequired = ((HandlerMethod) handler).getMethodAnnotation(PermissionRequired.class);
            if (permissionRequired == null)
                return true;
            String username = userService.verifyLogin((String) request.getSession().getAttribute(UserService.kTokenAttributeName));
            String permissionName = Permission.getPermissionName(((HandlerMethod) handler).getMethod(), permissionRequired);
            boolean allow = permissionService.isUserHasPermission(username, permissionName);
            if (allow) {
                log.info("User: "+username+" pass " +permissionName);
                return true;
            }
            else {
                ObjectMapper mapper = new ObjectMapper();
                BaseResponse baseResponse = new BaseResponse();
                baseResponse.throwout("need permission", 2);
                mapper.writeValue(response.getWriter(), baseResponse);
                log.info("User: "+username+" intercepted " +permissionName);
            }
            return false;
        }
        return true;
    }
}
