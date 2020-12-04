package spring.cloud.security.oauth.uaa.jwt.sso.config;

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
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
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

    private final JwtAccessTokenConverter jwtAccessTokenConverter;


    public AuthorizationServerConfig(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, OAuthUserDetailsService authUserDetailsService, TokenStore tokenStore, ClientDetailsService jdbcClientDetailsService, JwtAccessTokenConverter jwtAccessTokenConverter) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.authUserDetailsService = authUserDetailsService;
        this.tokenStore = tokenStore;
        this.jdbcClientDetailsService = jdbcClientDetailsService;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
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
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(authUserDetailsService)
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter);
    }


    /**
     * 在令牌端点定义安全约束
     * 允许表单验证，浏览器直接发送post请求即可获取tocken
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }
}
