package cn.styxs.personalweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "gmt_created", updatable = false)
    @CreationTimestamp
    private Date createTime;
    @Column(name = "gmt_modified")
    @UpdateTimestamp
    private Date modifiedTime;
}
