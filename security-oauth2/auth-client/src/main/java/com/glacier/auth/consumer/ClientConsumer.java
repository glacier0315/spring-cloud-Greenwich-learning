package com.glacier.auth.consumer;

import com.glacier.auth.consumer.fallback.UserConsumerFallback;
import com.glacier.auth.entity.dto.OauthClientDto;
import com.glacier.common.core.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-16 17:11
 */
@FeignClient(value = "auth-server", path = "/uas/api/client", fallback = UserConsumerFallback.class)
public interface ClientConsumer {

    /**
     * 更新客户端密码
     *
     * @param detailsDto
     * @return
     */
    @PostMapping("/updateSecret")
    HttpResult<String> updateSecret(@RequestBody OauthClientDto detailsDto);

    /**
     * 查找客户端
     *
     * @return
     */
    @GetMapping("/list")
    HttpResult<List<BaseClientDetails>> list();
}
