package spring.cloud.security.oauth.cloud.uaa.sso;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@ComponentScan(basePackages = {"spring.cloud.security.oauth"})
@Configuration
@EnableApolloConfig
public class UaaCloudSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UaaCloudSsoApplication.class, args);
    }
}