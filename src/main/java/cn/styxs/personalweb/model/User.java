package cn.styxs.personalweb.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
