package cn.styxs.topilog.service;

import cn.styxs.topilog.model.Permission;
import cn.styxs.topilog.model.User;
import cn.styxs.topilog.repository.PermissionRepository;
import cn.styxs.topilog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 提供权限相关功能(权限部分还依赖于RoleService)
 */
@Service
@Slf4j
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    UserRepository userRepository;

    // 查看指定权限是否存在，不存在则插入
    // 返回是否插入
    public boolean verifyAndInsertPermission(Permission permission) {
        if (permission != null) {
            String permissionName = permission.getName();
            if (permissionName != null && !"".equals(permissionName)) {
                if (!permissionRepository.existsByName(permission.getName())) {
                    permissionRepository.save(permission);
                    return true;
                }
            }
        }
        return false;
    }

    // 查看用户是否拥有指定权限
    public boolean isUserHasPermission(String username, String permissionName) {
        if (username == null || permissionName == null) {
            return false;
        }
        User user = userRepository.findUserByUsername(username);
        if(user == null || user.getRoles() == null)
            return false;
        Permission permission = permissionRepository.findByBelongRolesInAndNameEquals(user.getRoles(), permissionName);
        return permission != null;
    }

    // 列出系统中所有权限
    public List<Permission> listPermissions() {
        return permissionRepository.findAllByOrderById();
    }
}
