/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: ResourceApplication
 * Author:   xutao
 * Date:     2020/11/17 15:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.resource.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/17
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ResourceJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceJwtApplication.class, args);
    }
}