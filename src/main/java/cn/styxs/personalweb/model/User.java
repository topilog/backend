package cn.styxs.personalweb.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: RBAC模型之用户
 */
@Entity
@Table(name = "user")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseModel {
    @Column(length = 32)
    private String username;
    @Column(length = 32)
    private String password;
    @Column
    private String salt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private List<Role> roles;

    public void addRole(Role role) {
        if (role != null) {
            if (roles == null) {
                roles = new ArrayList<>();
            }
            roles.add(role);
        }
    }

}
