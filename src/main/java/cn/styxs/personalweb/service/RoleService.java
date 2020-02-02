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

    public Role createRole(String roleName) {
        Role role = Role.builder().roleName(roleName).build();
        return roleRepository.save(role);
    }

    @Transactional
    public Role addPermissions(String roleName, List<Permission> permissions) {
        Role role = roleRepository.findByRoleName(roleName);
        role.getPermissions().addAll(permissions);
        roleRepository.save(role);
        log.info(role.toString());
        return role;
    }

    @Transactional
    public User addRolesToUser(String username, String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        User user = userRepository.findUserByUsername(username);
        user.getRoles().add(role);
        userRepository.save(user);
        return user;
    }
}
