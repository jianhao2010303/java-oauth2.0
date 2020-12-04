/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: ResourceAdapterConfig
 * Author:   xutao
 * Date:     2020/12/3 15:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.zuul.jwt.sso.config;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/12/3
 * @since 1.0.0
 */

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 认证服务器资源
 */

/**
 * 系统服务器资源
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //跨域配置开始GlobalCorsConfig
                .authorizeRequests().antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

}
