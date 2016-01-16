package com.note.management.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.Mapping;

import com.note.management.security.common.AuthorityType;

/**
 * 权限控制接口类
 *
 * @date 2014年6月28日
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface Authority {

    /**
     * 权限类型
     *
     * @return
     */
    AuthorityType type();

}
