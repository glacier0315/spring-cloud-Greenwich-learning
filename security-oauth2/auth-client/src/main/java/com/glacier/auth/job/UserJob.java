package com.glacier.auth.job;

import com.glacier.auth.consumer.AuthUserConsumer;
import com.glacier.auth.consumer.UserConsumer;
import com.glacier.auth.entity.User;
import com.glacier.common.http.HttpResult;
import com.glacier.common.page.PageRequest;
import com.glacier.common.page.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 定时任务  测试客户端模式
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-14 16:54
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserJob {

    public static final int OK = 200;
    private final UserConsumer userConsumer;
    private final AuthUserConsumer authUserConsumer;

    /**
     * 调度任务执行，测试客户端模式
     * 0/20 * * * * ?  每20秒执行一次
     */
    @Scheduled(cron = "0/20 * * * * ?")
    public void findList() {
        log.info("测试资源服务器");
        HttpResult<List<User>> result = userConsumer.findList(User.builder()
                .username(String.valueOf(System.currentTimeMillis()))
                .build());
        Date time = Calendar.getInstance().getTime();
        if (result != null) {
            log.info("客户端模式成功返回，当前时间是 {}", time);
            if (result.getCode() == OK) {
                log.info("返回结果: ");
                if (result.getData() != null && !result.getData().isEmpty()) {
                    for (User user : result.getData()) {
                        log.info("====> {}", user);
                    }
                }
            } else {
                log.info("返回结果失败: {}", result.getMsg());
            }
        } else {
            log.warn("客户端模式返回失败，当前时间是 {}", time);
        }
    }

    /**
     * 调度任务执行，测试客户端模式
     * 0/20 * * * * ?  每20秒执行一次
     */
    @Scheduled(cron = "10/20 * * * * ?")
    public void findPage() {
        log.info("测试认证中心");
        HttpResult<PageResponse<User>> result = authUserConsumer.findPage(new PageRequest<>(1, 2, User.builder().build()));
        Date time = Calendar.getInstance().getTime();
        if (result != null) {
            log.info("客户端模式成功返回，当前时间是 {}", time);
            if (result.getCode() == OK) {
                log.info("返回结果: {} ", result.getData());
            } else {
                log.info("返回结果失败: {}", result.getMsg());
            }
        } else {
            log.warn("客户端模式返回失败，当前时间是 {}", time);
        }
    }
}