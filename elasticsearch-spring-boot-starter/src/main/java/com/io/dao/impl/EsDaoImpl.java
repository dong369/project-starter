package com.io.dao.impl;

import com.io.dao.EsDaoI;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project -
 *
 * @author guodd
 * @version 1.0
 * @date 2020/3/30
 * @since 1.8
 */
@Repository
public class EsDaoImpl<T> implements EsDaoI<T> {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean save(T t) throws Exception {
        return false;
    }

    @Override
    public boolean save(List<T> ts) throws Exception {
        return false;
    }
}
