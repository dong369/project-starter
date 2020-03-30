package com.io.autoconfigure;

import com.io.annotation.EnableESConfig;
import com.io.properties.EsProperties;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;

/**
 * Project -
 *
 * @author guodd
 * @version 1.0
 * @date 2020/3/30
 * @since 1.8
 */
@Configuration
@ConditionalOnBean(annotation = EnableESConfig.class)
@EnableConfigurationProperties(value = {EsProperties.class})
public class EsConfig {
    @Resource
    private EsProperties esProperties;

    private HttpHost[] getHttpHosts(String host) {
        String[] hosts = host.split(",");
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for (int i = 0; i < httpHosts.length; i++) {
            String h = hosts[i];
            httpHosts[i] = new HttpHost(h.split(":")[0], Integer.parseInt(h.split(":")[1]), "http");
        }
        return httpHosts;
    }

    public RestHighLevelClient client() {
        RestHighLevelClient client;
        HttpHost[] httpHosts;
        // 如果ES集群使用nginx做负载均衡，则直接使用对外的链接地址
        if (!esProperties.getEsHostPort().contains("http")) {
            httpHosts = getHttpHosts(esProperties.getEsHostPort());
        } else {
            httpHosts = new HttpHost[]{new HttpHost(esProperties.getEsHostPort())};
        }
        // x-park插件用户授权配置，7.1开始，Security功能免费使用
        if (esProperties.getXParkEnable()) {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esProperties.getUserName(), esProperties.getPassword()));
            client = new RestHighLevelClient(RestClient.builder(httpHosts).setHttpClientConfigCallback(httpAsyncClientBuilder
                    -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));
        } else {
            client = new RestHighLevelClient(RestClient.builder(httpHosts));
        }
        return client;
    }

    @Scope("singleton")
    @Bean(destroyMethod = "close")
    @Primary
    public RestHighLevelClient clientInstance() {
        return this.client();
    }

    @Bean
    public RestClient esClientInit() {
        HttpHost[] httpHosts = getHttpHosts(esProperties.getEsHostPort());
        return RestClient.builder(httpHosts).build();
    }
}
