package com.glacier.auth.job;

import com.glacier.auth.consumer.ClientConsumer;
import com.glacier.auth.entity.dto.OauthClientDto;
import com.glacier.common.core.http.HttpResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-16 17:20
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientJob {
    public static final int OK = 200;
    private final ClientConsumer clientConsumer;

    /**
     * 调度任务执行，测试客户端模式
     */
    @Scheduled(cron = "10 0/1 * * * ?")
    public void list() {
        log.info("测试认证中心");
        HttpResult<List<BaseClientDetails>> result = clientConsumer.list();
        Date time = Calendar.getInstance().getTime();
        if (result != null) {
            log.info("客户端模式成功返回，当前时间是 {}", time);
            if (result.getCode() == OK) {
                log.info("返回结果: {} ", result.getData().toArray());
            } else {
                log.info("返回结果失败: {}", result.getMsg());
            }
        } else {
            log.warn("认证中心客户端模式返回失败，当前时间是 {}", time);
        }
    }

    /**
     * 调度任务执行，测试客户端模式
     */
    @Scheduled(cron = "20 0/1 * * * ?")
    public void updateSecret() {
        log.info("测试认证中心");
        HttpResult<String> result = clientConsumer.updateSecret(new OauthClientDto("order-client", "order-client"));
        Date time = Calendar.getInstance().getTime();
        if (result != null) {
            log.info("客户端模式成功返回，当前时间是 {}", time);
            if (result.getCode() == OK) {
                log.info("返回结果: {} ", result);
            } else {
                log.info("返回结果失败: {}", result.getMsg());
            }
        } else {
            log.warn("认证中心客户端模式返回失败，当前时间是 {}", time);
        }
    }
}
