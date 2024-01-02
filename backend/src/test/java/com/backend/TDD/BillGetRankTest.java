package com.backend.TDD;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BillGetRankTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "1")
                        .param("year", "2023")
                        .param("month", "11")
                        .param("flag", "false"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("year", "2023")
                        .param("month", "11")
                        .param("flag", "false"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noYear() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "1")
                        .param("month", "11")
                        .param("flag", "false"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noMonth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "1")
                        .param("year", "2023")
                        .param("flag", "false"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noExistUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "913")
                        .param("year", "2023")
                        .param("month", "11")
                        .param("flag", "false"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void validCase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "1")
                        .param("year", "2023")
                        .param("month", "12")
                        .param("flag", "true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "-38")
                        .param("year", "2023")
                        .param("month", "12")
                        .param("flag", "true"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "1")
                        .param("year", "1863")
                        .param("month", "12")
                        .param("flag", "true"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "1")
                        .param("year", "2077")
                        .param("month", "12")
                        .param("flag", "true"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "1")
                        .param("year", "2023")
                        .param("month", "-5")
                        .param("flag", "true"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "1")
                        .param("year", "2023")
                        .param("month", "13")
                        .param("flag", "true"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}