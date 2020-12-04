/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: UuaJwtApplication
 * Author:   xutao
 * Date:     2020/11/24 9:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.uaa.jwt;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/24
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"spring.cloud.security.oauth"})
@EnableDiscoveryClient
@Configuration
@EnableApolloConfig
public class UuaJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(UuaJwtApplication.class, args);
    }
}