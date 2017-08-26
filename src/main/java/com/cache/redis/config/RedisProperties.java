package com.cache.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description: redis集群配置文件
 * @Author: yangbin
 * @Date: 上午 11:27 2017-8-26
 */
@Component
@ConfigurationProperties(prefix = "yc.redis.pool")
@PropertySource("classpath:application.yml")
public class RedisProperties {
    /** redis集群节点 */
    private String nodes;
    /** 连接超时时间 */
    private int timeout;
    /** 重连次数 */
    private int maxAttempts;
    public String getNodes() {
        return nodes;
    }
    public void setNodes(String nodes) {
        this.nodes = nodes;
    }
    public int getTimeout() {
        return timeout;
    }
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    public int getMaxAttempts() {
        return maxAttempts;
    }
    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
