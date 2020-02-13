package cn.styxs.personalweb.configuration;

import cn.styxs.personalweb.annotation.EnablePermission;
import cn.styxs.personalweb.annotation.PermissionRequired;
import cn.styxs.personalweb.model.Permission;
import cn.styxs.personalweb.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Map;

/*
    用于发现所有权限名并添加到数据库当中
 */
@Configuration
@Slf4j
public class PermissionAnnotationConfigurer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    PermissionService permissionService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Map<String, Object> map = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(EnablePermission.class);
        log.info("-----Start Scan PermissionRequired-----");
        for (Object obj : map.values()) {
            Method[] methods = obj.getClass().getMethods();
            for (Method method : methods) {
                PermissionRequired permissionRequired = AnnotationUtils.getAnnotation(method, PermissionRequired.class);
                if (permissionRequired != null) {
                    handlePermission(method, permissionRequired);
                }
            }
        }
        log.info("-----End Scan PermissionRequired------");
    }

    private void handlePermission(Method method, PermissionRequired permissionRequired) {
        String name = Permission.getPermissionName(method, permissionRequired);
        Permission permission = Permission.builder().name(name).description(permissionRequired.description()).build();
        permissionService.verifyAndInsertPermission(permission);
        log.info(permission.getName() + ": " + permission.getDescription());
    }
}
