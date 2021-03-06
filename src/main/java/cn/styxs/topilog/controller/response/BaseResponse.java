package cn.styxs.topilog.controller.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 所有api接口返回信息；upCode表示业务层错误码，baseCode表示下层(例如登录层、权限层)错误码，description为错误处打印的
 *      消息，帮助debug错误，status表示调用是否成功，resp携带接口真正的返回信息；如果status不为true，不应当读取resp的值
 */
@Getter
@Setter
@ToString
public class BaseResponse<T> {
    private boolean status;
    @ApiModelProperty(value = "业务层错误码", notes = "0表示成功")
    private int upCode;
    @ApiModelProperty(value = "下层错误码", notes = "0表示成功")
    private int baseCode;
    private String description;
    // 承载请求结果
    private T resp;

    public void succeed(T resp) {
        this.resp = resp;
        status = true;
        description = "";
        upCode = 0;
        baseCode = 0;
    }

    // 上层业务失败
    public void failed(String des, int errorCode) {
        status = false;
        description = des;
        upCode = errorCode;
        baseCode = 0;
    }

    // 下层业务失败
    public void throwout(String des, int errorCode) {
        status = false;
        description = des;
        upCode = -1;
        baseCode = errorCode;
    }
}
