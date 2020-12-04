package spring.cloud.security.oauth.cloud.resource.config;

/**
 * Copyright (C), 2002-2018, 北京二六三企业通信有限公司
 * package : com.net263.vcs.core.conf
 * FileName: RedisConfig
 * Author:   xutao
 * Date:     2018/8/21 14:11
 * Description: redis conf
 * History:
 * <author>          <time>          <version>          <desc>
 * xutao           修改时间           版本号              描述
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import spring.cloud.security.oauth.cloud.resource.config.prop.RedisProperties;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;


@Configuration
@ConditionalOnClass(JedisCluster.class)
public class RedisConfig {

    @Resource
    private RedisProperties redisProperties;

    @Bean(name = "jedisPoolConfig")
    JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig pool = new JedisPoolConfig();
        pool.setMaxIdle(redisProperties.getCluster().getMaxIdle());
        pool.setMinIdle(redisProperties.getCluster().getMinIdle());
        pool.setBlockWhenExhausted(redisProperties.getCluster().isBlockWhenExhausted());
        pool.setMaxTotal(redisProperties.getCluster().getMaxTotal());
        pool.setMaxWaitMillis(redisProperties.getCluster().getMaxWait());
        return pool;
    }

    /**
     * 集群配置
     *
     * @return
     */
    @Bean(name = "redisClusterConfiguration")
    public RedisClusterConfiguration getRedisClusterConfiguration() {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration();
        Set<RedisNode> nodes = new HashSet<RedisNode>();

        String[] nodec = redisProperties.getCluster().getNodes().split(",");
        for (int i = 0; i < nodec.length; i++) {
            String hostName = nodec[i].split(":")[0];
            int port = Integer.parseInt(nodec[i].split(":")[1]);
            RedisNode node = new RedisNode(hostName, port);
            nodes.add(node);
        }
        configuration.setClusterNodes(nodes);
        configuration.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());
        return configuration;
    }


    /**
     * 单机配置
     *
     * @return
     */
    @Bean(name = "redisStandaloneConfiguration")
    public RedisStandaloneConfiguration getRedisStandalongConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHostname());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        if (!StringUtils.isEmpty(redisProperties.getPassword())) {
            redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        return redisStandaloneConfiguration;
    }

    @Bean(name = "jedisConnectionFactory")
    JedisConnectionFactory jedisConnectionFactory(@Qualifier("redisStandaloneConfiguration") RedisStandaloneConfiguration standaloneConfiguration,
                                                  @Qualifier("redisClusterConfiguration") RedisClusterConfiguration clusterConfig,
                                                  @Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        //单机模式
        if (redisProperties.getModel() == 1) {
            JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
            jedisClientConfiguration.connectTimeout(Duration.ofMillis(redisProperties.getTimeout()));//  connection timeout
            factory = new JedisConnectionFactory(standaloneConfiguration, jedisClientConfiguration.build());
        } else if (redisProperties.getModel() == 2) {
            //哨兵模式
        } else if (redisProperties.getModel() == 3) {
            //Cluster模式
            factory = new JedisConnectionFactory(clusterConfig, jedisPoolConfig);
            //	判断密码是否存在，存在设置值
            checkPasswordIfNull(factory);
        }
        return factory;
    }

    @Bean
    public JedisCluster getJedisCluster(@Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        String[] nodec = redisProperties.getCluster().getNodes().split(",");
        for (int i = 0; i < nodec.length; i++) {
            String hostName = nodec[i].split(":")[0];
            int port = Integer.parseInt(nodec[i].split(":")[1]);
            HostAndPort node = new HostAndPort(hostName, port);
            nodes.add(node);
        }
        if (!StringUtils.isEmpty(redisProperties.getPassword()) && redisProperties.getModel() == 3) {
            return new JedisCluster(nodes, redisProperties.getTimeout(), redisProperties.getTimeout(), 5, redisProperties.getPassword(), jedisPoolConfig);
        } else {
            return new JedisCluster(nodes, redisProperties.getTimeout(), jedisPoolConfig);
        }
    }


    /**
     * 设置数据存入redis 的序列化方式
     * redisTemplate序列化默认使用的jdkSerializeable
     * 存储二进制字节码，导致key会出现乱码，所以自定义序列化类
     */
    @Bean
    @ConditionalOnMissingBean(name = {"redisTemplate"})
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setValueSerializer(jackson2JsonRedisSerializer());
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * json序列化
     *
     * @return
     */
    @Bean
    public RedisSerializer<Object> jackson2JsonRedisSerializer() {
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        return serializer;
    }


    /**
     * 校验password是否为空，不为空设置密码
     *
     * @param jedisConnectionFactory
     */
    private void checkPasswordIfNull(JedisConnectionFactory jedisConnectionFactory) {
        if (!StringUtils.isEmpty(redisProperties.getPassword())) {
            jedisConnectionFactory.setPassword(redisProperties.getPassword());
        }
    }
}
