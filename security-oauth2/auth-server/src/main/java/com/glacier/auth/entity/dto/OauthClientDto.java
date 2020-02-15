package com.glacier.auth.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 客户端密码 封装类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-15 14:49
 */
@Data
@ToString
public class OauthClientDto {
    private String clientId;
    private String clientSecret;
}
