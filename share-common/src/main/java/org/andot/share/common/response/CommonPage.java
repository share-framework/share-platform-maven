package org.andot.share.common.response;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.andot.share.common.type.ErrorCodeType;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;

/**
 * PageHelper分页数据封装类
 *
 * @author lucas
 * @date 2019-12-29 09:50:49
 */
@Getter
@Setter
@NoArgsConstructor
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> data;
    private long code;
    private String message;
    private long timestamp = LocalTime.now().getLong(ChronoField.NANO_OF_DAY);

    protected CommonPage(long code, String message, List<T> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        result.setCode(ErrorCodeType.SUCCESS.intValue());
        if(list == null){
            return result;
        }
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;
    }

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<>();
        result.setCode(ErrorCodeType.SUCCESS.intValue());
        if(pageInfo == null){
            return result;
        }
        result.setTotalPage(pageInfo.getPages());
        result.setTotal(pageInfo.getTotal());
        result.setData(pageInfo.getResult());
        return result;
    }

    /**
     * 失败返回结果
     *
     * @param resultCode 错误码
     */
    public static <T> CommonPage<T> failed(ResultCode resultCode) {
        return new CommonPage<T>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonPage<T> failed(String message) {
        return new CommonPage<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonPage<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonPage<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonPage<T> validateFailed(String message) {
        return new CommonPage<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }
}
