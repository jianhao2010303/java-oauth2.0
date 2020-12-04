package spring.cloud.security.oauth.cloud.uua;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ys
 * @date 2020/4/22 13:22
 */
@SpringBootApplication
@ComponentScan(basePackages = {"spring.cloud.security.oauth"})
@EnableDiscoveryClient
@Configuration
@EnableApolloConfig
public class UaaCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(UaaCloudApplication.class,args);
  }


}
