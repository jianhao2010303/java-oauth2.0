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
package spring.cloud.security.oauth.resource.config;

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
public class TokenConfiguration {

    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}