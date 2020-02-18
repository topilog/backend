package cn.styxs.personalweb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 该注解表明Controller中存在PermissionRequired注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnablePermission {
}
