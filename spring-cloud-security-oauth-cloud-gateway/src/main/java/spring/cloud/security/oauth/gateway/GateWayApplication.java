package spring.cloud.security.oauth.gateway;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

/**
 * @author ys
 * @date 2020/4/23 16:27
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@Configuration
@EnableApolloConfig
public class GateWayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GateWayApplication.class,args);
  }

}
