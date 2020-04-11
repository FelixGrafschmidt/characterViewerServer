package moe.character_viewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class CharacterViewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharacterViewerApplication.class, args);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		var config = new RedisStandaloneConfiguration();
		config.setHostName("localhost");
		config.setPort(6379);
		return new JedisConnectionFactory(config);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
