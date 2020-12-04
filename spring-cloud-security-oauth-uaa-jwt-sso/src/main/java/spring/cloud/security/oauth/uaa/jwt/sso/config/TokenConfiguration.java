/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: TokenConfiguration
 * Author:   xutao
 * Date:     2020/11/17 15:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.uaa.jwt.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/17
 * @since 1.0.0
 */
@Configuration
public class TokenConfiguration {

    @Bean
    public TokenStore tokenStore() {
        // Jwt令牌存储方案
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * JWT加密
     *
     * @return
     */
    @Primary
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }


    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        /* 采用非对称加密方式 私钥加密 */
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("oauth.jks"), "oauth00".toCharArray());
        return keyStoreKeyFactory.getKeyPair("oauth", "oauth00".toCharArray());
    }
}