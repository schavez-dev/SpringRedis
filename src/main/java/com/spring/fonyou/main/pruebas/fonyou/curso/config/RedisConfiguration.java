package com.spring.fonyou.main.pruebas.fonyou.curso.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.spring.fonyou.main.pruebas.fonyou.curso.model.Estudiante;

@Configuration
public class RedisConfiguration {
	/**
	 * Return new instance of redis standalone Configuration.
	 *
	 * @return redis standalone Configuration.
	 */
	@Bean
	public RedisStandaloneConfiguration redisStandaloneConfiguration() {
		return new RedisStandaloneConfiguration("127.0.0.1", 6379);
	}

	/**
	 * Return new pool config for lettuce client.
	 *
	 * @param redisLettuceProperties Properties.
	 * @return GenericObjectPoolConfig
	 */
	@Bean
	public GenericObjectPoolConfig poolConfig(final RedisLettuceProperties redisLettuceProperties) {
		final GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setBlockWhenExhausted(true);
		poolConfig.setMaxWaitMillis(-1);
		poolConfig.setFairness(true);
		poolConfig.setMinEvictableIdleTimeMillis(60000);
		poolConfig.setMaxTotal(300);
		poolConfig.setMaxIdle(30);
		poolConfig.setMinIdle(0);
		return poolConfig;
	}

	/**
	 * Return new instance of Connection Factory (Used Letucce).
	 *
	 * @return redis connection factory.
	 */
	@Bean
	@Primary
	public RedisConnectionFactory connectionFactory(final RedisStandaloneConfiguration redisStandaloneConfiguration,
			final GenericObjectPoolConfig poolConfig, final RedisLettuceProperties redisLettuceProperties) {
		return new LettuceConnectionFactory(redisStandaloneConfiguration,
				getLettuceClientConfiguration(poolConfig, redisLettuceProperties.getCommandTimeoutSeconds()));
	}

	/**
	 * Return new instance of Connection Factory (Used Letucce).
	 *
	 * @param redisStandaloneConfiguration standalone configuration.
	 * @param poolConfig                   pool configuration.
	 * @param redisLettuceProperties       redis lettuce properties.
	 * @return redis connection factory.
	 */
	@Bean
	public RedisConnectionFactory connectionFactoryReadiness(
			final RedisStandaloneConfiguration redisStandaloneConfiguration, final GenericObjectPoolConfig poolConfig,
			final RedisLettuceProperties redisLettuceProperties) {
		return new LettuceConnectionFactory(redisStandaloneConfiguration,
				getLettuceClientConfiguration(poolConfig, redisLettuceProperties.getCommandTimeoutReadinessSeconds()));
	}

	private static LettuceClientConfiguration getLettuceClientConfiguration(GenericObjectPoolConfig poolConfig,
			Integer commandTimeoutReadinessSeconds) {
		return LettucePoolingClientConfiguration.builder().poolConfig(poolConfig)
				.commandTimeout(Duration.of(commandTimeoutReadinessSeconds, ChronoUnit.SECONDS)).build();
	}

	/**
	 * Return redis Template ( Data Provision ).
	 *
	 * @return redis Template.
	 */
	@Bean
	@Primary
	public RedisTemplate<Object, Estudiante> redisTemplateDataProvisionDto(RedisConnectionFactory connectionFactory) {
		final RedisTemplate<Object, Estudiante> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

//	@Bean
//  JedisConnectionFactory conecJedisFactory() {
//	  return new JedisConnectionFactory();
//  }
//  
//  @Bean
//  RedisTemplate<String, Estudiante> redisTemplate(){
//	  final RedisTemplate<String, Estudiante> redisTemp = new RedisTemplate<>();
//	  redisTemp.setConnectionFactory(conecJedisFactory());
//	  return redisTemp;
//  }
}
