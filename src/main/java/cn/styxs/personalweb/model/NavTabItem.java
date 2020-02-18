package cn.styxs.personalweb.model;

import lombok.*;

import javax.persistence.Column;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 导航栏
 */
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class NavTabItem {
    @Column(name = "url")
    private String navigateToUrl;
    @Column(name = "title")
    private String title;
}
