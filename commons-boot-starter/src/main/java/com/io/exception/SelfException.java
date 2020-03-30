package com.io.exception;

/**
 * Project -
 *
 * @author guodd
 * @version 1.0
 * @date 2020/3/30
 * @since 1.8
 */
public class SelfException extends RuntimeException {
    /**
     * 属性描述：code
     */
    private Integer code;

    public SelfException(String message) {
        super(message);
    }

    public SelfException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
