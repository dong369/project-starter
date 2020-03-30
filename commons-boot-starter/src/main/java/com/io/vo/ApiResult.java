package com.io.vo;

import java.util.HashMap;

/**
 * Project -
 *
 * @author guodd
 * @version 1.0
 * @date 2020/3/30
 * @since 1.8
 */
public class ApiResult<String, T> extends HashMap<String, T> {
    /**
     * 属性描述：message
     */
    private String message;

    /**
     * 属性描述：code
     */
    private Integer code;

    /**
     * 属性描述：对象
     */
    private T data;
}
