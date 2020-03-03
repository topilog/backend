package cn.styxs.topilog.configuration;

import cn.styxs.topilog.annotation.PermissionRequired;
import cn.styxs.topilog.model.Permission;
import cn.styxs.topilog.service.PermissionService;
import cn.styxs.topilog.service.URIAccessService;
import cn.styxs.topilog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 权限拦截器，拦截访问带PermissionRequired注解的接口但不具有对应权限的请求
 */
@Slf4j
public class PermissionControlInterceptor extends CustomInterceptor{
    private PermissionService permissionService;

    public PermissionControlInterceptor(URIAccessService uriAccessService, UserService userService, PermissionService permissionService) {
        super(uriAccessService, userService);
        this.permissionService = permissionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String username = getUsernameFromRequest(request);
        if (handler instanceof HandlerMethod) {
            PermissionRequired permissionRequired = ((HandlerMethod) handler).getMethodAnnotation(PermissionRequired.class);
            if (permissionRequired == null)
                return true;
            String permissionName = Permission.getPermissionName(((HandlerMethod) handler).getMethod(), permissionRequired);
            boolean allow = permissionService.isUserHasPermission(username, permissionName);
            if (!allow) {
                // 如果不允许则返回错误response
                out(response, "need permission", 2);
            }
            logP(allow, username, permissionName, request.getRequestURI());
            return allow;
        } else {
            String uri = request.getRequestURI();
            if (!uriAccessService.canAccessWithoutPermission(uri)) {
                String permissionName = uriAccessService.getPermissionWithURI(uri);
                boolean allow = permissionService.isUserHasPermission(username, permissionName);
                if (!allow) {
                    out(response, "need permission", 2);
                }
                logP(allow, username, permissionName, uri);
                return allow;
            }
            logP(true, username, null, uri);
            return true;
        }

    }

    private void logP(boolean allow, String username, String permissionName, String uri) {
        StringBuilder sb = new StringBuilder();
        if (username != null) {
            sb.append("User: ").append(username);
        }
        if (permissionName != null) {
            sb.append(" ");
            sb.append("Permission: ").append(permissionName);
        }
        if (uri != null) {
            sb.append(" ");
            sb.append("uri: ").append(uri);
        }
        log(allow, sb.toString());
    }
}
