/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: ResourceServerConfig
 * Author:   xutao
 * Date:     2020/12/3 17:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.uaa.jwt.sso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/12/3
 * @since 1.0.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //antMatcher表示只能处理/user的请求
                .antMatcher("/user/**")
                .authorizeRequests()
                .antMatchers("/user/test1").permitAll()
                .antMatchers("/user/test2").authenticated();
    }
}