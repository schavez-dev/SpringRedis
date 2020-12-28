package com.spring.fonyou.main.pruebas.fonyou.curso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import com.spring.fonyou.main.pruebas.fonyou.curso.model.Estudiante;

@Configuration
public class RedisConfiguration {
  @Bean
  JedisConnectionFactory conecJedisFactory() {
	  return new JedisConnectionFactory();
  }
  
  @Bean
  RedisTemplate<Object, Estudiante> redisTemplate(){
	  final RedisTemplate<Object, Estudiante> redisTemp = new RedisTemplate<>();
	  redisTemp.setConnectionFactory(conecJedisFactory());
	  return redisTemp;
  }
}
