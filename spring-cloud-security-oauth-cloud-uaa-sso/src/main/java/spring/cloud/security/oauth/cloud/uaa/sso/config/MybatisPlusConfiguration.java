/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: MybatisPlusConfiguration
 * Author:   xutao
 * Date:     2020/11/10 10:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.cloud.uaa.sso.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/10
 * @since 1.0.0
 */
@Configuration
@MapperScan("spring.cloud.security.oauth.manager.dao")
public class MybatisPlusConfiguration {

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        return new MybatisPlusInterceptor();
    }
}