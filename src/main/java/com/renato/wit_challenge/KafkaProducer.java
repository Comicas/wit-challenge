package com.renato.wit_challenge;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Service responsible for sending messages to Kafka topics.
 * 
 * @author Renato
 */

@Service
public class KafkaProducer {

    //Kafka template to send messages to the Kafka topic
    private KafkaTemplate<String, String> kafkaTemplate;

    //Constructor to initialize the Kafka template
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //Method to send a message to a specific Kafka topic
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
