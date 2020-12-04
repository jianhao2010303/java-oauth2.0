package spring.cloud.security.oauth.cloud.uaa.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 使用Redis管理令牌
 *
 * @author ys
 * @date 2020/4/22 15:06
 */
@Configuration
public class TokenConfig {

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    public TokenConfig(JwtAccessTokenConverter jwtAccessTokenConverter) {
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        //通过Redis管理令牌
        return new JwtTokenStore(jwtAccessTokenConverter);
    }
}
