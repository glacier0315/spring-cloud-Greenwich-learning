package com.glacier.auth.consumer.fallback;

import com.glacier.auth.consumer.UserConsumer;
import com.glacier.auth.entity.User;
import com.glacier.common.http.HttpResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务熔断
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-13 22:26
 */
@Component
public class UserConsumerFallback implements UserConsumer {
    @Override
    public HttpResult<List<User>> findList(User user) {
        return HttpResult.error(500, "服务熔断了", new ArrayList<>(1));
    }
}
