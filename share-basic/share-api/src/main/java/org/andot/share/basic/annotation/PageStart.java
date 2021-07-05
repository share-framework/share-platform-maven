package org.andot.share.basic.annotation;

import java.lang.annotation.*;

/**
 * 分页启动注解
 * @author andot
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PageStart {
}
