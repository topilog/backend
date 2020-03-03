package cn.styxs.topilog.repository;

import cn.styxs.topilog.model.Permission;
import cn.styxs.topilog.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description:
 */
public interface PermissionRepository extends CrudRepository<Permission, Long> {
    boolean existsByName(String name);

    Permission findByName(String name);

    List<Permission> findByBelongRolesInOrderByName(List<Role> roles);

    List<Permission> findAllByOrderById();

    Permission findByBelongRolesInAndNameEquals(List<Role> roles, String permissionName);
}
