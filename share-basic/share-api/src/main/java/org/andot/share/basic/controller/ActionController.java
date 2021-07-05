package org.andot.share.basic.controller;

import io.jsonwebtoken.lang.Collections;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.andot.share.basic.annotation.PageStart;
import org.andot.share.basic.async.TestAsync;
import org.andot.share.basic.service.ActionService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.andot.share.basic.dto.ActionDTO;
import org.andot.share.basic.dto.PageDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/***
 * @author lucas
 */
@Slf4j
@Api(tags = "操作方法API", description = "操作方法请求控制器")
@RequestMapping("/action")
@RestController
public class ActionController {
    @Resource
    private ActionService actionService;

    @ApiOperation("添加数据")
    @PutMapping("")
    public CommonResult save(@RequestBody ActionDTO actionDTO) {
        if(actionService.saveAction(actionDTO)) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("添加失败，请重试！");
        }
    }

    @ApiOperation("更新数据")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody ActionDTO actionDTO) {
        actionDTO.setActionId(id);
        if(actionService.updateAction(actionDTO)) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public CommonResult del(@PathVariable("id") Long id) {
        if(actionService.delActionById(id)) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("删除失败，请重试！");
        }
    }

    @ApiOperation("批量删除数据")
    @DeleteMapping("/dels")
    public CommonResult dels(@RequestBody List<Long> ids) {
        List<Long> idsNo = actionService.delActionById(ids);
        if(Collections.isEmpty(idsNo)) {
            return CommonResult.success();
        } else {
            return CommonResult.success(idsNo, "有部分删除失败，请重试没有删除的！");
        }
    }

    @ApiOperation("根据id获取数据")
    @GetMapping("/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return CommonResult.success(actionService.getActionById(id));
    }

    @ApiOperation("根据条件获取列表数据")
    @GetMapping("/list")
    public CommonResult getList(ActionDTO actionDto) {
        return CommonResult.success(actionService.getActionList(actionDto));
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PageStart
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDTO<ActionDTO> actionPage) {
        List<ActionDTO> actionDTOList = actionService.getActionListOfPage(actionPage);
        return CommonPage.restPage(actionDTOList);
    }
}
