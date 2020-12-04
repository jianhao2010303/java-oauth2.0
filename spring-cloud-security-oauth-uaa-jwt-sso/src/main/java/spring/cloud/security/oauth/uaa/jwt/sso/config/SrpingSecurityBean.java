package spring.cloud.security.oauth.uaa.jwt.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.security.KeyPair;


/**
 * 统一管理 bena
 *
 * @author ys
 * @date 2020/4/22 13:36
 */
@Configuration
public class SrpingSecurityBean {


    public static final String SIGNING_KEY = "cloud-oauth2-key";

    @Resource
    public DataSource dataSource;

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //JDBC管理用户信息
    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        JdbcClientDetailsService client = new JdbcClientDetailsService(dataSource);
        client.setPasswordEncoder(passwordEncoder());
        return client;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}
