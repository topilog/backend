package cn.styxs.topilog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 基础Entity, 所有Entity应当继承这个；提供id、创建时间、最近修改时间字段
 */
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
