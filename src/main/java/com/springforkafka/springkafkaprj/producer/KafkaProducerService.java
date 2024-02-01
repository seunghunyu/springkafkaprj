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

    public void producerMessage(){
        log.info("Produce Message - BEGIN");
        String message = String.format("메세지를 전송 하였습니다.");
        kafkaTemplate.send("TEST_TOPICc", message);
        log.info("Produce Message - END {}", message);
    }
}
