/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: TokenConfiguration
 * Author:   xutao
 * Date:     2020/11/17 15:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.resource.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import spring.cloud.security.oauth.resource.jwt.config.properties.JwtProperties;

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

    private final JwtProperties jwtProperties;


    @Autowired
    public TokenConfiguration(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Bean
    public TokenStore tokenStore() {
        // Jwt令牌存储方案
        return new JwtTokenStore(assessTokenConverter());
    }


    @Bean
    public JwtAccessTokenConverter assessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(jwtProperties.getSecret());//
        return converter;
    }
}