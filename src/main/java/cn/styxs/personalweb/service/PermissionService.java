package cn.styxs.personalweb.service;

import cn.styxs.personalweb.annotation.PermissionRequired;
import cn.styxs.personalweb.model.Permission;
import cn.styxs.personalweb.model.Role;
import cn.styxs.personalweb.model.User;
import cn.styxs.personalweb.repository.PermissionRepository;
import cn.styxs.personalweb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

@Service
@Slf4j
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    UserRepository userRepository;

    public boolean verifyAndInsertPermission(Permission permission) {
        if (!permissionRepository.existsByName(permission.getName())) {
            permissionRepository.save(permission);
            return true;
        }
        return false;
    }

    public boolean isUserHasPermission(String username, String permissionName) {
        User user = userRepository.findUserByUsername(username);
        log.info(user+" "+permissionName);
        Permission permission = permissionRepository.findByBelongRolesInAndNameEquals(user.getRoles(), permissionName);
        log.info(user.toString());
        log.info(permissionName);
        log.info(permission.toString());
        if (permission != null)
            return true;
        return false;
    }

    public List<Permission> listPermissions() {
        return permissionRepository.findAllByOrderById();
    }
}
