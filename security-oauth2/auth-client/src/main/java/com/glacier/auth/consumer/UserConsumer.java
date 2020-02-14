package com.glacier.auth.consumer;

import com.glacier.auth.consumer.fallback.UserConsumerFallback;
import com.glacier.auth.entity.User;
import com.glacier.common.core.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户消费者
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-13 22:25
 */
@FeignClient(value = "auth-resource", path = "/user", fallback = UserConsumerFallback.class)
public interface UserConsumer {

    /**
     * 查找用户
     *
     * @param user
     * @return
     */
    @PostMapping("/findList")
    HttpResult<List<User>> findList(@RequestBody User user);
}
