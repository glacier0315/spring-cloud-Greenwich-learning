package com.glacier.auth.consumer.fallback;

import com.glacier.auth.consumer.AuthUserConsumer;
import com.glacier.auth.entity.User;
import com.glacier.common.http.HttpResult;
import com.glacier.common.page.PageRequest;
import com.glacier.common.page.PageResponse;
import org.springframework.stereotype.Component;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-14 18:20
 */
@Component
public class AuthUserConsumerFallback implements AuthUserConsumer {
    @Override
    public HttpResult<PageResponse<User>> findPage(PageRequest<User> pageRequest) {
        return HttpResult.error(500, "服务熔断了",
                PageResponse.<User>builder()
                        .current(pageRequest.getCurrent())
                        .build());
    }
}
