package cn.styxs.topilog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 该注解标识一个接口需要进行权限验证录;所在class需要声明@EnablePermission才能生效
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@LoginRequired // 合并注解：权限一定需要登录才能校验
public @interface PermissionRequired {
    boolean value() default true;
    String permissionName() default "";
    String description() default "";
}
