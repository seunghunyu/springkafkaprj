package com.springforkafka.springkafkaprj;

import com.springforkafka.springkafkaprj.config.kafka.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value={KafkaProperties.class})
@SpringBootApplication
public class SpringkafkaprjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringkafkaprjApplication.class, args);
	}

}
