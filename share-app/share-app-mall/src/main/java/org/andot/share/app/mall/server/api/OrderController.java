package org.andot.share.app.mall.server.api;

import org.andot.share.common.response.CommonPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/page")
    public CommonPage getPageList(){
        return CommonPage.restPage(null);
    }
}
