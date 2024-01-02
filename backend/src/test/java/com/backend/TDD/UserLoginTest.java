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
class UserLoginTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noPhone() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("password", "123"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("phone", "12345678910"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void successLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("phone", "12345678910")
                        .param("password", "123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void wrongAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("phone", "12345678910")
                        .param("password", "IamWrongPW"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void validCase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("phone", "12345678910")
                        .param("password", "123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("phone", "")
                        .param("password", "123"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("phone", "32165492154632")
                        .param("password", "123"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("phone", "12345678910")
                        .param("password", ""))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("phone", "12345678910")
                        .param("password", "16sdf15352g1sd5sf353zd2sdf"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}