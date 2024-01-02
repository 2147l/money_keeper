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
class BillGetThisWeekTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noExistUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "37")
                        .param("flag", "0"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noFlag() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "37"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void thisWeek() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "1")
                        .param("flag", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void lastWeek() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "1")
                        .param("flag", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void last2Week() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "1")
                        .param("flag", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidFlag() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "1")
                        .param("flag", "5"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void validCase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "1")
                        .param("flag", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "-37")
                        .param("flag", "0"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "1")
                        .param("flag", "-3"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "1")
                        .param("flag", "7"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}