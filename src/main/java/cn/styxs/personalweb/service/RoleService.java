package cn.styxs.personalweb.service;

import cn.styxs.personalweb.model.Permission;
import cn.styxs.personalweb.model.Role;
import cn.styxs.personalweb.model.User;
import cn.styxs.personalweb.repository.RoleRepository;
import cn.styxs.personalweb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    public boolean createRole(String roleName) {
        Role role = Role.builder().roleName(roleName).build();
        roleRepository.save(role);
        return true;
    }

    @Transactional
    public boolean addPermission(String roleName, Permission permission) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null)
            return false;
        role.addPermission(permission);
        roleRepository.save(role);
        return true;
    }

    @Transactional
    public boolean addPermissions(String roleName, List<Permission> permissions) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null)
            return false;
        role.addPermissions(permissions);
        roleRepository.save(role);
        return true;
    }

    @Transactional
    public boolean addRolesToUser(String username, String roleName) {
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
