package spring.cloud.security.oauth.sso.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * 统一管理 bena
 *
 * @author ys
 * @date 2020/4/22 13:36
 */
@Configuration
public class SrpingSecurityBean {
    /**
     * JWT加密
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //采用对称加密进行签名令牌 资源服务器也要使用此秘钥来解密 校验令牌合法性
//    jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);
        //采用非对称加密方式 私钥加密
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("oauth2.jks"), "oauth2".toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("oauth2"));
        return jwtAccessTokenConverter;
    }

}
