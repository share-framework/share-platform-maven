package org.andot.share.app.line.config;

import org.andot.share.app.line.exception.LineIdNotFoundException;
import org.andot.share.common.response.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(LineIdNotFoundException.class)
    public CommonResult lineIdNotFound(LineIdNotFoundException lineIdNotFoundException) {
        return CommonResult.failed(lineIdNotFoundException.getMessage());
    }
}
