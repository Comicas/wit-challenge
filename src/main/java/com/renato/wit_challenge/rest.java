package com.renato.wit_challenge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * REST Controller that handles HTTP requests for calculator operations.
 * Receives requests from clients and sends back JSON responses.
 * 
 * @author Renato
 */
@RestController
public class rest {
    
    //Kafka producer to send messages to the Kafka topic
    @Autowired
    private KafkaProducer kafkaProducer;

    //Calculator object to perform the calculations~
    @Autowired
    private calculator calculator;
    

    private static final Logger logger = LoggerFactory.getLogger(rest.class);


    //Endpoint to add two numbers
    @GetMapping(value = "/sum", produces = "application/json")
    public String sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        logger.info("Received sum request: a={}, b={}", a, b);

        try {
            BigDecimal result = calculator.sum(a, b);
            kafkaProducer.sendMessage("result", result.toString());
            logger.info("Sum operation completed successfully. Result: {}", result);
            return "{\"result\": " + result + "}";

        } catch (Exception e) {
            logger.error("Error during sum operation with a={}, b={}", a, b, e);
            throw e;
        }
    }

    //Endpoint to subtract two numbers
    @GetMapping(value = "/subtract", produces = "application/json")
    public String subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        logger.info("Received subtract request: a={}, b={}", a, b);
        try {

            BigDecimal result = calculator.subtract(a, b);
            kafkaProducer.sendMessage("result", result.toString());
            logger.info("Subtract operation completed successfully. Result: {}", result);
            return "{\"result\": " + result + "}";

        } catch (Exception e) {
            logger.error("Error during subtract operation with a={}, b={}", a, b, e);
            throw e;
        }
    }

    //Endpoint to multiply two numbers
    @GetMapping(value = "/multiply", produces = "application/json")
    public String multiply(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        logger.info("Received multiply request: a={}, b={}", a, b);
        try {

            BigDecimal result = calculator.multiply(a, b);
            kafkaProducer.sendMessage("result", result.toString());
            logger.info("Multiply operation completed successfully. Result: {}", result);
            return "{\"result\": " + result + "}";

        } catch (Exception e) {
            logger.error("Error during multiply operation with a={}, b={}", a, b, e);
            throw e;
        }
    }
    //Endpoint to divide two numbers
    @GetMapping(value = "/divide", produces = "application/json")
    public String divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        logger.info("Received divide request: a={}, b={}", a, b);
        try {

            if (b.compareTo(BigDecimal.ZERO) == 0) {
                logger.warn("Division by zero attempted with a={}, b={}", a, b);
                return "{\"error\": \"Division by zero not allowed\"}";
            }

            BigDecimal result = calculator.divide(a, b);
            kafkaProducer.sendMessage("result", result.toString());
            logger.info("Divide operation completed successfully. Result: {}", result);
            return "{\"result\": " + result + "}";

        } catch (Exception e) {
            logger.error("Error during divide operation with a={}, b={}", a, b, e);
            throw e;
        }
    }
}
