package com.solms.PharmaPartnersOpdracht;
/*

        Author: Johan Solms - johan.solms@gmail.com

 */
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MockTesting {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/test")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hallo")));
    }

    @Test
    public void shouldReturnPreparedCurrency() throws Exception{
        mockMvc.perform( MockMvcRequestBuilders
                .get("/currencies")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("{\"id\":1,\"ticker\":\"BTC\",\"name\":\"Bitcoin\",\"coinNumber\":16770000,\"marketCap\":189580000000")));
    }

    @Test
    public void should404WhenNotFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/currencies?id=10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }

    @Test
    public void shouldCreateCurrencyWhenValid() throws Exception{
        mockMvc.perform(post("/currencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"ticker\": \"X45\"," +
                        "\"name\": \"XML\"," +
                        "\"coinNumber\": 16670001," +
                        "\"marketCap\": 69000" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.ticker").value("X45"))
                .andExpect(jsonPath("$.name").value("XML"));
    }

    @Test
    public void shouldDeleteWhenValid() throws Exception {
        mockMvc.perform(delete("/currencies?id=3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldDeleteWhenInValid() throws Exception {
        mockMvc.perform(delete("/currencies?id=10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}