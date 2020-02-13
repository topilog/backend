package cn.styxs.personalweb.annotation;

/*
* 标识一个接口需要进行权限验证
* class需要声明@EnablePermission才能生效
* */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@LoginRequired // 合并注解：权限一定需要登录才能校验
public @interface PermissionRequired {
    boolean value() default true;
    String permissionName() default "";
    String description() default "";
}
