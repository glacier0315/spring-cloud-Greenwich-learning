package com.glacier.auth.config;

import com.alibaba.fastjson.JSONWriter;
import com.glacier.common.core.http.HttpResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器配置
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-09 17:04
 */
@Configuration
@EnableResourceServer
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 指定范围为/api
        http.requestMatchers()
                .antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpStatus.OK.value());
                    response.setContentType("application/json;charset=utf-8");
                    response.setCharacterEncoding("UTF-8");
                    JSONWriter jsonWriter = new JSONWriter(response.getWriter());
                    jsonWriter.writeObject(HttpResult.<String>error(403, "权限不足！"));
                    jsonWriter.flush();
                    jsonWriter.close();
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpStatus.OK.value());
                    response.setContentType("application/json;charset=utf-8");
                    response.setCharacterEncoding("UTF-8");
                    JSONWriter jsonWriter = new JSONWriter(response.getWriter());
                    jsonWriter.writeObject(HttpResult.<String>error(401, "未进行认证！"));
                    jsonWriter.flush();
                    jsonWriter.close();
                });
    }
}
