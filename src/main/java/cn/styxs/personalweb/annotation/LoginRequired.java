package cn.styxs.personalweb.annotation;

import java.lang.annotation.*;

/*
* 标识一个方法需要登录
* 未登录的请求将直接被拦截
* */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
    boolean value() default true;
}
