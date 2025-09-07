package com.renato.wit_challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class calculatorTest {

    private calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new calculator();
    }

    @Test
    void testSum() {
        BigDecimal a = new BigDecimal("2.5");
        BigDecimal b = new BigDecimal("3.5");
        BigDecimal expected = new BigDecimal("6.0");
        
        BigDecimal actual = calculator.sum(a, b);
        
        assertEquals(0, expected.compareTo(actual), "Sum should be 6.0");
    }

    @Test
    void testSumWithNegativeNumbers() {
        BigDecimal a = new BigDecimal("-5");
        BigDecimal b = new BigDecimal("3");
        BigDecimal expected = new BigDecimal("-2");
        
        BigDecimal actual = calculator.sum(a, b);
        
        assertEquals(0, expected.compareTo(actual), "Sum should be -2");
    }

    @Test
    void testSubtract() {
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("3");
        BigDecimal expected = new BigDecimal("7");
        
        BigDecimal actual = calculator.subtract(a, b);
        
        assertEquals(0, expected.compareTo(actual), "Subtract should be 7");
    }

    @Test
    void testMultiply() {
        BigDecimal a = new BigDecimal("4");
        BigDecimal b = new BigDecimal("3");
        BigDecimal expected = new BigDecimal("12");
        
        BigDecimal actual = calculator.multiply(a, b);
        
        assertEquals(0, expected.compareTo(actual), "Multiply should be 12");
    }

    @Test
    void testDivide() {
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("2");
        BigDecimal expected = new BigDecimal("5");
        
        BigDecimal actual = calculator.divide(a, b);
        
        assertEquals(0, expected.compareTo(actual), "Divide should be 5");
    }

    @Test
    void testDivideByZero() {
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("0");
        
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(a, b);
        }, "Division by zero should throw ArithmeticException");
    }
}
