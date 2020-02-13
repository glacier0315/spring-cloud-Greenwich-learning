package com.glacier.auth.utils;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 加密测试
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-14 16:33
 */
public class PasswordEncoderTest {

    @Test
    public void test() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "auth-client";
        String encode = passwordEncoder.encode(rawPassword);
        System.out.println(rawPassword + "\t加密后密码：\t" + encode);
    }
}
