package cn.styxs.topilog.annotation;

import java.lang.annotation.*;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 该注解表明方法需要登录;未登录的请求应当被拦截
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
    boolean value() default true;
}
