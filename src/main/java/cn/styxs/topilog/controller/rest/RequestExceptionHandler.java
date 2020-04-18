package cn.styxs.topilog.controller.rest;

import cn.styxs.topilog.controller.response.BaseResponse;
import cn.styxs.topilog.model.ErrorCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/17
 * @Description: 用于处理@Valid校验参数异常情况下的结果显示
 */
@RestControllerAdvice
public class RequestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleArgumentValidException() {
        BaseResponse response = new BaseResponse();
        response.failed("argument valid failed", ErrorCode.kIllegalArgument);
        return response;
    }
}
