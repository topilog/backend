package cn.styxs.personalweb.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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
