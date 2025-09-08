package com.renato.wit_challenge;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for calculator operations.
 * 
 * @author Renato
 */

public class calculator {

    private static final Logger logger = LoggerFactory.getLogger(calculator.class);

    //Method to sum two numbers
    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.add(b);
        logger.debug("Sum result: {}", result);
        return result;
    }

    //Method to subtract two numbers
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.subtract(b);
        logger.debug("Subtract result: {}", result);
        return result;
    }
    
    //Method to multiply two numbers
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.multiply(b);
        logger.debug("Multiply result: {}", result);
        return result;
    }
    
    //Method to divide two numbers
    public BigDecimal divide(BigDecimal a, BigDecimal b) {

        if (b.compareTo(BigDecimal.ZERO) == 0) {
            logger.error("Division by zero attempted: {} / {}", a, b);
            throw new ArithmeticException("Division by zero");
        }
        
        BigDecimal result = a.divide(b);
        logger.debug("Divide result: {}", result);
        return result;
    }
}
