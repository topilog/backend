package cn.styxs.personalweb.model;

import lombok.*;

import javax.persistence.Column;

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
