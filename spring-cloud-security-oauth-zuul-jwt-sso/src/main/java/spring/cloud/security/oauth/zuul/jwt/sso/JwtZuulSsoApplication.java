/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: JwtZuulSsoApplication
 * Author:   xutao
 * Date:     2020/12/1 10:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.zuul.jwt.sso;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/12/1
 * @since 1.0.0
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@Configuration
@EnableApolloConfig
public class JwtZuulSsoApplication  {

    public static void main(String[] args) {
        SpringApplication.run(JwtZuulSsoApplication.class, args);
    }
}