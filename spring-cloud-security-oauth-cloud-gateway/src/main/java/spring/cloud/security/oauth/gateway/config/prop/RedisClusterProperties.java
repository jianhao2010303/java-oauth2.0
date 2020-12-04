/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: RedisClusterProperties
 * Author:   xutao
 * Date:     2020/2/13 17:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.gateway.config.prop;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author user
 * @create 2020/2/13
 * @since 1.0.0
 */
@Data
public class RedisClusterProperties {

    private String nodes;

    private Integer maxTotal;

    private Integer maxAttempts;

    private Integer maxRedirects;

    private Integer maxActive;

    private Integer maxWait;

    private Integer maxIdle;

    private Integer minIdle;

    private boolean testOnBorrow;

    private boolean blockWhenExhausted;
}