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
package spring.cloud.security.oauth.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

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

    // 令牌存储策略
    @Bean
    public TokenStore tokenStore() {
        // 内存方式，生成普通令牌
        return new InMemoryTokenStore();
    }
}