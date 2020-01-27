package cn.styxs.personalweb.controller.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseResponse {
    private boolean status;
    @ApiModelProperty(value = "业务层状态", notes = "0表示成功")
    private int upCode;
    @ApiModelProperty(value = "下层状态", notes = "0表示成功")
    private int baseCode;
    private String description;

    public void succeed() {
        status = true;
        description = "";
        upCode = 0;
        baseCode = 0;
    }

    public void failed(String des, int errorCode) {
        status = false;
        description = des;
        upCode = errorCode;
        baseCode = 0;
    }

    public void throwout(String des, int errorCode) {
        status = false;
        description = des;
        upCode = -1;
        baseCode = errorCode;
    }
}
