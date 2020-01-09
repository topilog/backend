package cn.styxs.personalweb.model;

import cn.styxs.personalweb.model.BaseModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "nav_tab")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class NavTabItem extends BaseModel implements Serializable {
    @Column(name = "url")
    private String navigateToUrl;
    @Column(name = "title")
    private String title;
}
