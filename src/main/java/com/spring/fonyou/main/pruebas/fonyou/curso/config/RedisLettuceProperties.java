package com.spring.fonyou.main.pruebas.fonyou.curso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.redis.lettuce.pool")

public class RedisLettuceProperties {
	
	private Integer minIdle;
	
	private Integer maxIdle;
	
	private Integer maxActive;
	
	private Integer maxWait;
	
	private Integer commandTimeoutSeconds;
	
	private Integer commandTimeoutReadinessSeconds;
	
	private Boolean fairness;
	
	private Integer minEvictableIdleTimeMillis;
	
	private Boolean blockWhenExhausted;
}
