package com.renato.wit_challenge;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service responsible for sending messages to Kafka topics.
 * 
 * @author Renato
 */

@Service
public class KafkaProducer {

    //Kafka template to send messages to the Kafka topic
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);


    //Constructor to initialize the Kafka template
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        logger.info("KafkaProducer initialized successfully");
    }

    //Method to send a message to a specific Kafka topic
    public void sendMessage(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message);
            logger.info("Message sent successfully to topic '{}': {}", topic, message);

        } catch (Exception e) {
            logger.error("Failed to send message to topic '{}': {}", topic, message, e);
            throw e;
        }
    }
}
