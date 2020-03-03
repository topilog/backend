package cn.styxs.topilog.service;

import cn.styxs.topilog.model.Permission;
import cn.styxs.topilog.model.Role;
import cn.styxs.topilog.model.User;
import cn.styxs.topilog.repository.RoleRepository;
import cn.styxs.topilog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 提供权限相关功能
 */
@Service
@Slf4j
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    /*
        创建一个权限角色
     */
    public boolean createRole(String roleName) {
        if (roleName == null || "".equals(roleName)) {
            return false;
        }
        Role role = Role.builder().roleName(roleName).build();
        roleRepository.save(role);
        return true;
    }

    /*
        为角色添加一个权限
        返回值表示是否成功添加
     */
    @Transactional
    public boolean addPermission(String roleName, Permission permission) {
        if (roleName == null || permission == null) {
            return false;
        }
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null)
            return false;
        role.addPermission(permission);
        roleRepository.save(role);
        return true;
    }

    /*
        为角色添加多个权限
        返回值表示是否成功添加
     */
    @Transactional
    public boolean addPermissions(String roleName, List<Permission> permissions) {
        if (roleName == null || permissions == null) {
            return  false;
        }
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null)
            return false;
        role.addPermissions(permissions);
        roleRepository.save(role);
        return true;
    }

    /*
        为某个用户添加一个角色
        返回值表示是否成功添加
     */
    @Transactional
    public boolean addRolesToUser(String username, String roleName) {
        if (username == null || roleName == null) {
            return false;
        }
        Role role = roleRepository.findByRoleName(roleName);
        User user = userRepository.findUserByUsername(username);
        if (role == null || user == null || user.getRoles() == null) {
            return false;
        }
        user.addRole(role);
        userRepository.save(user);
        return true;
    }
}
