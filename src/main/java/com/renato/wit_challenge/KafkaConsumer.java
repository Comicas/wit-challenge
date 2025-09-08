package com.renato.wit_challenge;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service responsible for consuming messages from Kafka topics.
 * 
 * @author Renato
 */

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    //Kafka listener to consume messages from the Kafka topic
    @KafkaListener(topics = "result", groupId = "test-group")
    public void listen(String message) {
        try {
            logger.info("Received message from Kafka topic 'result': {}", message);

            System.out.println("Received message: " + message);
            
            logger.debug("Message processing completed for: {}", message);
        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", message, e);
            throw e;
        }
    }
}