package com.springforkafka.springkafkaprj.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaTemplateConfiguration {
    private final KafkaProperties kafkaProperties;

    @Autowired
    private TaskExecutorConfig taskExecutorConfig;

    @Bean
    public KafkaTemplate<String,Object> kafkaProdTemplate() {
        return new KafkaTemplate<String,Object>(producerFactory());
    }

    @Bean
    public ProducerFactory<String,Object> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootStrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<String,Object>(config);
    }

    @Bean
    public ConsumerFactory<String,Object> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootStrapServers());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "TEST-CONSUMING-GROUP");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_DOC, "latest");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<String,Object>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaConsumerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        // �⺻ ���丮 ����
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3); // �ϳ��� �����ʿ��� ������ 3���� ó��.

        // ���� Ŀ��
        // �����ʿ��� acknowledgment�� ȣ��� �� ����, Ŀ��
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        // ������ ������ ó��
//        factory.getContainerProperties().setConsumerTaskExecutor(taskExecutorConfig.executor());

        // retry
//        factory.setRetryTemplate(retryTemplate());
//        factory.setRecoveryCallback(context -> {
//            System.out.println("consumer retry : " + context.toString());
//            return null;
//        });
//        factory.setErrorHandler(new SeekToCurrentErrorHandler());

        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
//    @Bean
//    RetryTemplate retryTemplate(){
//        RetryTemplate retryTemplate = new RetryTemplate();
//
//        // retry ��å
//        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
//        fixedBackOffPolicy.setBackOffPeriod(1000L);
//
//        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
//        retryPolicy.setMaxAttempts(3);
//
//        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
//        retryTemplate.setRetryPolicy(retryPolicy);
//
//        return retryTemplate;
//    }
}
