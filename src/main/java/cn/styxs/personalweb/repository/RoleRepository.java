package cn.styxs.personalweb.repository;

import cn.styxs.personalweb.model.Permission;
import cn.styxs.personalweb.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
