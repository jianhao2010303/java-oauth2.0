/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: AuthorizationConfiguration
 * Author:   xutao
 * Date:     2020/11/17 15:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/17
 * @since 1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientDetailsService;


    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    // 配置客户端详细信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 使用内存存储
                .withClient("c1") // client_id
                // 客户端密钥
                .secret(new BCryptPasswordEncoder().encode("secret"))
                // 资源列表
                .resourceIds("res1")
                // 授权方式
                .authorizedGrantTypes("authorization_code","password","client_creentials","implicit","refresh_token")
                // 允许授权的范围
                .scopes("all")
                //
                .autoApprove(false)  // false 跳转到授权页面
                // 加上验证回调地址
                .redirectUris("http://www.baidu.com");

    }



    // 令牌管理服务
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);  // 客户端信息服务
        service.setSupportRefreshToken(true);   // 是否产生刷新令牌
        service.setTokenStore(tokenStore);  // 设置令牌存储策略
        service.setAccessTokenValiditySeconds(7200);// 令牌默认有效期 2 小时
        service.setRefreshTokenValiditySeconds(259200);
        return service;
    }

    /**
     * /oauth/authorize     授权端点
     * /oauth/token         令牌断点
     * /oauth/confirm-access用户确认授权提交端点
     * /auth/error          授权服务错误信息断电
     * /auth/check_token    用户资源服务访问的令牌解析断电
     * /oauth/token_key     提供公有密钥的端点，如果你使用jwt令牌的话
     */

    /**
     *  令怕i访问端点
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 密码管理模式
                .authenticationManager(authenticationManager)
                // 授权码模式
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenServices()) // 令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); // 允许post提交
    }


    /**
     *  令牌访问端点的安全策略
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")         // /oauth/token_key  公开
                .checkTokenAccess("permitAll()")       // /auth/check_token  检测令牌
                .allowFormAuthenticationForClients();  // 允许通过表单认证，申请令牌

        super.configure(security);
    }
}