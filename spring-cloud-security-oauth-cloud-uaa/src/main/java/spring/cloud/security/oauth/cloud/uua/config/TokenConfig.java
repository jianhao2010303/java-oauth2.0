package spring.cloud.security.oauth.cloud.uua.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 使用Redis管理令牌
 *
 * @author ys
 * @date 2020/4/22 15:06
 */
@Configuration
public class TokenConfig {

    private final JedisConnectionFactory redisConnectionFactory;

    @Autowired
    public TokenConfig(@Qualifier("jedisConnectionFactory") JedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public TokenStore tokenStore() {
        //通过Redis管理令牌
        return new RedisTokenStore(redisConnectionFactory);
    }


}
