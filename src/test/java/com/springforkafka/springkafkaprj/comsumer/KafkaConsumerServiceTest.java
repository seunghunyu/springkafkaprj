package com.springforkafka.springkafkaprj.comsumer;

import com.springforkafka.springkafkaprj.producer.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class KafkaConsumerServiceTest {
    @Autowired
    KafkaProducerService producer;
    @Autowired
    KafkaConsumerService consumer;

    @Test
    void consumingMessage() throws InterruptedException {
        String topic = "TEST_TOPIC";
        String payload = "test producing and consuming!!";

        producer.producerMessage(topic, payload);
        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        log.info("@@@@@@@@@@@@@@@@@@Payload Info = {}", consumer.getPayload());
//        assertTrue(messageConsumed);
//        assertThat(consumer.getPayload(). contains(payload));


    }
}