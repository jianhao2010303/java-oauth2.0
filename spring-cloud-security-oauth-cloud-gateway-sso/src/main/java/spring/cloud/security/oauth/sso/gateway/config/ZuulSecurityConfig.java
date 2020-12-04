/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: ZuulSecurityConfig
 * Author:   xutao
 * Date:     2020/12/1 9:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.sso.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutao
 * @create 2020/12/1
 * @since 1.0.0
 */
@Configuration
@EnableResourceServer
public class ZuulSecurityConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "system-server";

    /**
     * 如果对安全校验不是很高得话，可以不配置这个 默认叫oauth2-resource
     **/
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(RESOURCE_ID);
    }

    /**
     * 配置除了 获取token 不需要认证 其他请求都需要认证
     * 根据自己得项目情况做配置 我这里是yml文件配置了前缀/token这个url是用来访问认证服务器
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(new ZuulAuditLogFilter(), ExceptionTranslationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/token/**").permitAll()
//                .anyRequest().authenticated();
    }
}