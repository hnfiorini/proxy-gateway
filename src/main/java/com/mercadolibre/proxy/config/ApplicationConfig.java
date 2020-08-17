package com.mercadolibre.proxy.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class ApplicationConfig {
//  @Bean
//  public RedisConnectionFactory redisConnectionFactory() {
//	  return new JedisConnectionFactory();
//  }
//  
//  @Bean
//  public RedisTemplate<?, ?> redisTemplate() {
//  RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
//  template.setConnectionFactory(redisConnectionFactory());
//  return template;
//  }
	
	@Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just("1");
    }
}
