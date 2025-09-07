package com.renato.wit_challenge;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service responsible for consuming messages from Kafka topics.
 * 
 * @author Renato
 */

@Service
public class KafkaConsumer {

    //Kafka listener to consume messages from the Kafka topic
    @KafkaListener(topics = "result", groupId = "test-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}