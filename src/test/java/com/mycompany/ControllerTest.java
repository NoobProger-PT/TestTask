package com.mycompany;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnCorrectAnswer() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/count?str=aaaaabcccc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("\"a\": 5, \"c\": 4, \"b\": 1"));
    }

    @Test
    void shouldReturnExceptionWithNullString() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/count?str="))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{'message':'Переданная строка пуста'}"));
    }

    @Test
    void shouldReturnExceptionWithOutRange() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/count?str=aaaaabccccaaaaabccccaaaaabcccc"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{'message':'count.str: Длина должна составлять от 1 до 10'}"));
    }
}
