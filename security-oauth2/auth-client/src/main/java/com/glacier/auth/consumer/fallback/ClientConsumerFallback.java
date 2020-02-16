package com.glacier.auth.consumer.fallback;

import com.glacier.auth.consumer.ClientConsumer;
import com.glacier.auth.entity.dto.OauthClientDto;
import com.glacier.common.core.http.HttpResult;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-16 17:14
 */
@Component
public class ClientConsumerFallback implements ClientConsumer {
    @Override
    public HttpResult<String> updateSecret(OauthClientDto detailsDto) {
        return HttpResult.error(500, "服务熔断了");
    }

    @Override
    public HttpResult<List<BaseClientDetails>> list() {
        return HttpResult.error(500, "服务熔断了");
    }
}
