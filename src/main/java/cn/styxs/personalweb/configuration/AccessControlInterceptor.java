package cn.styxs.personalweb.configuration;

import cn.styxs.personalweb.annotation.LoginRequired;
import cn.styxs.personalweb.controller.response.BaseResponse;
import cn.styxs.personalweb.service.PermissionService;
import cn.styxs.personalweb.service.URIAccessService;
import cn.styxs.personalweb.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 访问控制器，用于登录验证
 */
@Slf4j
public class AccessControlInterceptor extends CustomInterceptor{
    public AccessControlInterceptor(URIAccessService uriAccessService, UserService userService) {
        super(uriAccessService, userService);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* 需要登录态的场景进行拦截 */
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Class declaringClass = method.getDeclaringClass();
            LoginRequired classRequired = AnnotationUtils.findAnnotation(declaringClass, LoginRequired.class);
            LoginRequired methodRequired = AnnotationUtils.findAnnotation(method, LoginRequired.class);
            if ((methodRequired != null && methodRequired.value() == true)
                    || ((classRequired != null && classRequired.value() == true) && (methodRequired == null || methodRequired.value() == true))) {
                if (getUsernameFromRequest(request) == null) {
                    notifyNeedLogin(request, response, declaringClass.getAnnotation(RestController.class) != null);
                    log(false);
                    return false;
                }
            }
        } else {
            String uri = request.getRequestURI();
            if (!uriAccessService.canAccessWithoutLogin(uri)) {
                if (getUsernameFromRequest(request) == null) {
                    notifyNeedLogin(request, response, false);
                    log(false);
                    return false;
                }
            }
        }
        log(true);
        return true;
    }

    private void notifyNeedLogin(HttpServletRequest request,HttpServletResponse response, boolean rest) throws Exception {
        if (rest) {
            out(response, "need login", 1);
        } else {
            response.sendRedirect("/user/login?source="+request.getRequestURI());
        }
    }
}
