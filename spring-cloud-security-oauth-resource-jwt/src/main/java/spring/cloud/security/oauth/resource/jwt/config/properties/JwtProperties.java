/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: JwtProperties
 * Author:   xutao
 * Date:     2020/11/11 12:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.resource.jwt.config.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/11
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RefreshScope
public class JwtProperties {

    private String header;

    private String secret;

    private  Long expiration;

    private String tokenHead;

    public String getTokenHead() {
        return tokenHead.concat(" ");
    }
}