package com.renato.wit_challenge;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(rest.class)
public class restTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private KafkaProducer kafkaProducer;

    @Test
    void testSum() throws Exception {
        doNothing().when(kafkaProducer).sendMessage(eq("result"), any(String.class));

        mockMvc.perform(get("/sum")
                .param("a", "5")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(content().json("{\"result\": 8}"));
    }

    @Test
    void testSubtract() throws Exception {
        doNothing().when(kafkaProducer).sendMessage(eq("result"), any(String.class));

        mockMvc.perform(get("/subtract")
                .param("a", "10")
                .param("b", "4"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(content().json("{\"result\": 6}"));
    }

    @Test
    void testMultiply() throws Exception {
        doNothing().when(kafkaProducer).sendMessage(eq("result"), any(String.class));

        mockMvc.perform(get("/multiply")
                .param("a", "6")
                .param("b", "7"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(content().json("{\"result\": 42}"));
    }

    @Test
    void testDivide() throws Exception {
        doNothing().when(kafkaProducer).sendMessage(eq("result"), any(String.class));

        mockMvc.perform(get("/divide")
                .param("a", "15")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(content().json("{\"result\": 5}"));
    }

    @Test
    void testInvalidParameters() throws Exception {
        mockMvc.perform(get("/sum")
                .param("a", "invalid")
                .param("b", "3"))
                .andExpect(status().isBadRequest());
    }
}
