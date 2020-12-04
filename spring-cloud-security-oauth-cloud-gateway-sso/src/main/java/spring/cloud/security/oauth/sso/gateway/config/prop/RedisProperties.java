package spring.cloud.security.oauth.sso.gateway.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2002-2018, 北京二六三企业通信有限公司
 * package : com.net263.vcs.core.conf.redis
 * FileName: RedisProperties
 * Author:   xutao
 * Date:     2018/8/21 18:59
 * Description: redis配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * xutao           修改时间           版本号              描述
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    /**
     * 使用模式
     */
    private Integer model;

    private Integer timeout;

    private String hostname;

    private String password;

    private Integer port;

    @NestedConfigurationProperty
    private RedisClusterProperties cluster;
}
