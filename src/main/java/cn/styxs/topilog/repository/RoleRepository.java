package cn.styxs.topilog.repository;

import cn.styxs.topilog.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description:
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
