package com.springforkafka.springkafkaprj.comsumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
@Setter
public class KafkaConsumerService {

    private CountDownLatch latch = new CountDownLatch(1);
    private String payload;

    @KafkaListener(topics = "TEST_TOPIC" , containerFactory = "kafkaConsumerFactory")
    public void consumingMessage(ConsumerRecord<String, Object> consumerRecord){
        payload = (String)consumerRecord.value();
        log.info("Consuming Message - BEGIN");
        log.info(payload);
        log.info("Consuming Message - END ");
    }
    public void resetLatch(){
        latch = new CountDownLatch(1);
    }
}
