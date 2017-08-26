package com.cache.redis.controller;

import com.cache.redis.template.JSONResult;
import com.cache.redis.template.YCRedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: Favorite控制层
 * @Author: yangbin
 * @Date: 上午 11:38 2017-8-26
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @Autowired
    private YCRedisTemplate ycRedisTemplate;

    /**
     * Favorite编辑
     * @Title: update
     * @return Object
     * @author wujing
     * @date 2017-07-13 15:09:50
     */
    @GetMapping("/get")
    public String getWithPrefix(@RequestParam String prefix, @RequestParam String key) {
        try {
            String str = ycRedisTemplate.getWithPrefix(prefix, key);
            log.debug("getWithPrefix:" + str);
            return JSONResult.callSuccess(str);
        } catch (Exception e) {
            log.error("查询异常:" + e.getMessage(), e);
            return JSONResult.callException(null);
        }
    }
    @PostMapping("/set")
    public String setWithPrefix(@RequestParam String prefix, @RequestParam String key, @RequestParam String value) {
        try {
            String str = ycRedisTemplate.setWithPrefix(prefix, key, value);
            return JSONResult.callSuccess(str);
        } catch (Exception e) {
            log.error("查询异常:" + e.getMessage(), e);
            return JSONResult.callException(null);
        }
    }
}
