package cn.styxs.topilog.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/25
 * @Description: 提供支持编辑展示顺序能力的Model类
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisplayOrderModel extends BaseModel{
    @Column(name = "main_order")
    private int mainOrder;
    @Column(name = "sub_order")
    private int subOrder;
}
