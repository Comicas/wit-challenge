package com.renato.wit_challenge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;

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

    //Calculator object to perform the calculations
    private calculator calculator = new calculator();

    //Endpoint to add two numbers
    @GetMapping(value = "/sum", produces = "application/json")
    public String sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = calculator.sum(a, b);
        kafkaProducer.sendMessage("result", result.toString());
        return "{\"result\": " + result + "}";
    }

    //Endpoint to subtract two numbers
    @GetMapping(value = "/subtract", produces = "application/json")
    public String subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = calculator.subtract(a, b);
        kafkaProducer.sendMessage("result", result.toString());
        return "{\"result\": " + result + "}";
    }

    //Endpoint to multiply two numbers
    @GetMapping(value = "/multiply", produces = "application/json")
    public String multiply(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = calculator.multiply(a, b);
        kafkaProducer.sendMessage("result", result.toString());
        return "{\"result\": " + result + "}";
    }

    //Endpoint to divide two numbers
    @GetMapping(value = "/divide", produces = "application/json")
    public String divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {

        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "{\"error\": \"Division by zero not allowed\"}";
        }
        
        BigDecimal result = calculator.divide(a, b);
        kafkaProducer.sendMessage("result", result.toString());
        return "{\"result\": " + result + "}";
    }
}
