package com.renato.wit_challenge;
import java.math.BigDecimal;

/**
 * Class for calculator operations.
 * 
 * @author Renato
 */

public class calculator {

    //Method to sum two numbers
    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.add(b);
        return result;
    }

    //Method to subtract two numbers
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.subtract(b);
        return result;
    }
    
    //Method to multiply two numbers
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.multiply(b);
        return result;
    }
    
    //Method to divide two numbers
    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.divide(b);
        return result;
    }
}
