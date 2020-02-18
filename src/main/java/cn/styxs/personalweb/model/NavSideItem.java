package cn.styxs.personalweb.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 侧边栏；目前未使用
 */
@Entity
@Table(name = "nav_side")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class NavSideItem extends BaseModel implements Serializable {
    @Column
    private int type;
    @Column(name = "src")
    private String source;
}
