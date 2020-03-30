package com.io.dao;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Project -
 *
 * @author guodd
 * @version 1.0
 * @date 2020/3/30
 * @since 1.8
 */
public class MainTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Test
    public void test() {
        System.out.println(restHighLevelClient);
    }
}
