package com.io.dao;

import java.util.List;

/**
 * Project -
 *
 * @author guodd
 * @version 1.0
 * @date 2020/3/30
 * @since 1.8
 */
public interface EsDaoI<T> {
    /*--------------------------------------CREATE-----------------------------------------*/

    /**
     * 新增，EsId重复则覆盖，相当于mysql的insert ...on duplicate key update
     *
     * @param t 索引对象
     * @throws Exception
     */
    boolean save(T t) throws Exception;

    boolean save(List<T> ts) throws Exception;
}