package cn.styxs.topilog.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: RBAC模型之角色类
 */
@Entity
@Table(name = "role")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseModel{
    @Column
    private String roleName;

    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "rid"),
            inverseJoinColumns = @JoinColumn(name = "pid")
    )
    private List<Permission> permissions;

    public void addPermission(Permission p) {
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
        permissions.add(p);
    }

    public void addPermissions(List<Permission> p) {
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
        permissions.addAll(p);
    }
}
