package cn.styxs.personalweb.configuration;

import cn.styxs.personalweb.annotation.LoginRequired;
import cn.styxs.personalweb.controller.response.BaseResponse;
import cn.styxs.personalweb.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class AccessControlInterceptor implements HandlerInterceptor {
    private UserService userService;
    public AccessControlInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* 需要登录态的场景进行拦截 */
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Class declaringClass = method.getDeclaringClass();
            LoginRequired classRequired = (LoginRequired) declaringClass.getDeclaredAnnotation(LoginRequired.class);
            LoginRequired methodRequired = method.getAnnotation(LoginRequired.class);
            if ((methodRequired != null && methodRequired.value() == true)
                    || ((classRequired != null && classRequired.value() == true) && (methodRequired == null || methodRequired.value() == true))) {
                if (!checkLogin(request)) {
                    notifyNeedLogin(request, response, declaringClass.getAnnotation(RestController.class) != null);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkLogin(HttpServletRequest request) {
        String token = (String)request.getSession().getAttribute(UserService.kTokenAttributeName);
        return userService.verifyLogin(token) != null;
    }

    private void notifyNeedLogin(HttpServletRequest request,HttpServletResponse response, boolean rest) throws Exception {
        if (rest) {
            ObjectMapper mapper = new ObjectMapper();
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.throwout("need login", 1);
            mapper.writeValue(response.getWriter(), baseResponse);
        } else {
            response.sendRedirect("/user/login?source="+request.getRequestURI());
        }
    }

}
