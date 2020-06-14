package org.andot.share.app.mall.mobile.api;

import org.andot.share.common.response.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainPageController {
    @GetMapping("/loopPic")
    public CommonResult getLoopPic(){
        return CommonResult.success(null);
    }
}
