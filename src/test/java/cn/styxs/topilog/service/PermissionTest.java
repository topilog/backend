package cn.styxs.topilog.service;

import cn.styxs.topilog.model.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class PermissionTest{
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;


    public static final String kUsername = "PermissionTest";
    /*
        添加测试用户
     */
    @Test
    public void addTestedUser() {
        userService.createUser(kUsername, "12345", "there_did_not_use_salt");
        Assert.isTrue(userService.isUserExist(kUsername), "can't create user");
    }

    @Test
    public void addPermission() {
        // 创建角色
        String roleName = "TestRole";
        roleService.createRole(roleName);

        List<Permission> permissions = permissionService.listPermissions();
        Assert.notNull(permissions, "there is no permission here");
        // 为角色添加权限
        roleService.addPermissions(roleName, permissions);
        // 为用户添加角色
        roleService.addRolesToUser(kUsername, roleName);
        // 判断用户是否具有相应权限
        for (Permission p : permissions) {
            Assert.isTrue(permissionService.isUserHasPermission(kUsername, p.getName()), "do not have permission: "+p.getName());
        }
    }



}