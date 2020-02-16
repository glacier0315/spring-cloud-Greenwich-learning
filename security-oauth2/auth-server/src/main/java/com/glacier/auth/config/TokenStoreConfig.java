package com.glacier.auth.config;

import com.glacier.auth.entity.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * tokenStore 配置
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-09 08:28
 */
@Configuration
@EnableConfigurationProperties({AuthorizationServerProperties.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenStoreConfig {
    private final UserDetailsService userDetailsService;
    private final AuthorizationServerProperties authorization;

    /**
     * 设置jwt 存储token
     *
     * @return
     */
    @Bean
    @Primary
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * jwt转换
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        Resource keyStore = new ClassPathResource(this.authorization.getJwt().getKeyStore());
        char[] keyStorePassword = this.authorization.getJwt().getKeyStorePassword().toCharArray();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, keyStorePassword);
        String keyAlias = this.authorization.getJwt().getKeyAlias();
        char[] keyPassword = (char[]) Optional.ofNullable(this.authorization.getJwt().getKeyPassword())
                .map(String::toCharArray)
                .orElse(keyStorePassword);
        // 使用非对称加密
        accessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword));
        return accessTokenConverter;
    }

    /**
     * jwt内容转换
     *
     * @return
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        // jwt令牌内容增强
        TokenEnhancer tokenEnhancer = (accessToken, authentication) -> {
            Map<String, Object> additionalInformation = accessToken.getAdditionalInformation();
            // 授权类型 authorization_code、password、client_credentials、refresh_token、implicit
            // 客户端模式
            String clientCredentials = "client_credentials";
            if (!clientCredentials.equals(authentication.getOAuth2Request().getGrantType())) {
                Object principal = authentication.getUserAuthentication().getPrincipal();
                if (principal instanceof UserDetailsDto) {
                    UserDetailsDto userDetailsDto = (UserDetailsDto) principal;
                    additionalInformation.put("userId", userDetailsDto.getUserId());
                } else {
                    // 解决refresh token 时 内容增强
                    UserDetailsDto userDetailsDto = (UserDetailsDto) userDetailsService.loadUserByUsername((String) principal);
                    additionalInformation.put("userId", userDetailsDto.getUserId());
                }
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };
        List<TokenEnhancer> delegates = new ArrayList<>(2);
        delegates.add(jwtAccessTokenConverter());
        delegates.add(tokenEnhancer);
        tokenEnhancerChain.setTokenEnhancers(delegates);
        return tokenEnhancerChain;
    }
}
