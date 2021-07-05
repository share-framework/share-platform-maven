package org.andot.share.basic.component.handler;

import com.github.pagehelper.PageHelper;
import org.andot.share.basic.dto.PageDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * page 开始拦截器
 * @author andot
 */
@Aspect
@Component
public class PageStartIntercept {

    @Pointcut(
            value = "execution(* org.andot.share.basic.controller..*.*(..))"
    )
    public void pageMethod(){

    }

    @Before("pageMethod() && @annotation(org.andot.share.basic.annotation.PageStart)")
    public void pageStartProcess(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        if(objects != null && objects.length > 0 && PageDTO.class.isInstance(objects[0])) {
            PageDTO pageDTO = (PageDTO) objects[0];
            PageHelper.startPage(pageDTO.getPage(), pageDTO.getRows());
        }
    }
}
