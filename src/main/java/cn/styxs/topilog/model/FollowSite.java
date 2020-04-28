package cn.styxs.topilog.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/16
 * @Description: 关注的站点信息（友情链接概念）
 */
@Entity
@Table(name = "follow_site")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FollowSite extends DisplayOrderModel {
    public static final int kMaxNameLength = 20;
    public static final int kMaxUrlLength = 255;
    public static final int kMaxIntroductionLength = 50;

    @Column(name = "name", length = kMaxNameLength)
    @NotEmpty @Size(max = kMaxNameLength)
    private String name;
    @Column(name = "url", length = kMaxUrlLength)
    @NotEmpty @Size(max = kMaxUrlLength)
    private String url;
    @Column(name = "info", length = kMaxIntroductionLength)
    @NotEmpty @Size(max = kMaxIntroductionLength)
    private String introduction;
}
