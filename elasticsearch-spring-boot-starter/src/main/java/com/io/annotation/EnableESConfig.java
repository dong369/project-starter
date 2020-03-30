package com.io.annotation;

import java.lang.annotation.*;

/**
 * Project -
 *
 * @author guodd
 * @version 1.0
 * @date 2020/3/16
 * @since 1.8
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableESConfig {
}
