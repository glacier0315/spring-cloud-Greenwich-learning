package com.glacier.auth.consumer;

import com.glacier.auth.consumer.fallback.UserConsumerFallback;
import com.glacier.auth.entity.User;
import com.glacier.common.core.http.HttpResult;
import com.glacier.common.core.page.PageRequest;
import com.glacier.common.core.page.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户消费者
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-13 22:25
 */
@FeignClient(value = "auth-server", path = "/uas/user", fallback = UserConsumerFallback.class)
public interface AuthUserConsumer {

    /**
     * 分页查找用户
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("findPage")
    HttpResult<PageResponse<User>> findPage(@RequestBody PageRequest<User> pageRequest);
}
