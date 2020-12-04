package spring.cloud.security.oauth.cloud.uaa.sso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import spring.cloud.security.oauth.manager.service.OAuthUserDetailsService;

/**
 * 认证配置类
 *
 * @author ys
 * @date 2020/4/22 13:30
 */
@Configuration
@EnableAuthorizationServer //开启 OAuth2 认证服务器功能
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    public final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final OAuthUserDetailsService authUserDetailsService;

    private final TokenStore tokenStore;

    private final ClientDetailsService jdbcClientDetailsService;

    public AuthorizationServerConfig(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, OAuthUserDetailsService authUserDetailsService, TokenStore tokenStore, ClientDetailsService jdbcClientDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.authUserDetailsService = authUserDetailsService;
        this.tokenStore = tokenStore;
        this.jdbcClientDetailsService = jdbcClientDetailsService;
    }

    /**
     * 配置允许访问此认证服务器的客户端信息
     * 1、内存方式
     * 2、数据库方式
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //JDBC管理客户端
        clients.withClientDetails(jdbcClientDetailsService);
    }


    /**
     * 关于认证服务器端点配置
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //密码模式需要这个实例
        endpoints.authenticationManager(authenticationManager);
        //刷新令牌需要使用该实例
        endpoints.userDetailsService(authUserDetailsService);
        //通过Redis管理令牌
        endpoints.tokenStore(tokenStore);
    }


    /**
     * 令牌安全端点配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //所有人可访问 /oauth/token_key 后面要获取公钥, 默认拒绝访问
        security.tokenKeyAccess("permitAll()");
        //认证后可访问 /oauth/check_token , 默认拒绝访问
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();
    }
}
