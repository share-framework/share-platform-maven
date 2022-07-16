package org.andot.share.common.exception;

import org.andot.share.common.response.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobeExceptionHandler {
    @ExceptionHandler(HaveSubItemException.class)
    public CommonResult haveSubItem(HaveSubItemException haveSubItemException) {
        return CommonResult.failed(haveSubItemException.getMessage());
    }
}
