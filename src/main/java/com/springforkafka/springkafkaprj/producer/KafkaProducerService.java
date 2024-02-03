package com.springforkafka.springkafkaprj.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
    private final KafkaTemplate kafkaTemplate;
    private static int runningId = 0;

    public void producerMessage(String topic, String payload){
        //log.info("sending payload = {} , Topic = {}", payload, topic);
        //kafkaTemplate.send("TEST_TOPIC", payload);
        kafkaTemplate.send(topic,payload);
    }
}
