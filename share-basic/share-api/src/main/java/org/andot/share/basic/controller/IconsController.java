package org.andot.share.basic.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.component.utils.CurrentUserUtil;
import org.andot.share.basic.dto.PageDto;
import org.andot.share.basic.entity.Icons;
import org.andot.share.basic.service.IconsService;
import org.andot.share.basic.service.IconsService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * 图标请求控制器
 * @author lucas
 */
@Api(tags = "图标API", description = "图标请求控制器")
@RequestMapping("/icons")
@RestController
public class IconsController {

    @Resource
    private IconsService iconsService;

    @PostMapping("")
    public CommonResult add(@RequestBody Icons icons) {
        if (iconsService.saveIcons(icons)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("更新数据")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody Icons icons) {
        if (iconsService.updateIcons(id, icons)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public CommonResult del(@PathVariable("id") Long id) {
        if (iconsService.delIconById(id)) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败，请重试！");
        }
    }

    @ApiOperation("根据条件获取列表数据")
    @GetMapping("/list")
    public CommonResult<List<Icons>> getList(Icons icons) {
        return CommonResult.success(iconsService.getIconList(icons));
    }
}