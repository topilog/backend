package cn.styxs.personalweb.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ContentResponse<T> extends BaseResponse {
    T content;
}
