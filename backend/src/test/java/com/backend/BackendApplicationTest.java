package com.backend;

import com.backend.entity.Bill;
import com.backend.entity.Budget;
import com.backend.entity.User;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BackendApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getById")
                        .param("userId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("phone", "123")
                        .param("password", "123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setPhone("13546584563");
        user.setPassword("123456");
        user.setUsername("小王同学");
        user.setAvatar("src/assets/avatar/2.png");
        user.setSex("男");
        user.setWechat("xiaowangtongxue");
        user.setEmail("xiaowang@email.com");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void testCreateBill() throws Exception {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(-66.6));
        bill.setDate("2021-3-27");
        bill.setNote("测试");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testDeleteBill() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/bill/delete")
                        .param("billId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testListById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/listById")
                        .param("userId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testListByYM() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/listByYM")
                        .param("userId", "1")
                        .param("year", "2023")
                        .param("month", "12"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetYearSum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getYearSum")
                        .param("userId", "1")
                        .param("year", "2023"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetYearMonthSum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getYearMonthSum")
                        .param("userId", "1")
                        .param("year", "2023"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testCreateFeedback() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/feedback/create")
                        .param("userId", "1")
                        .param("content", "测试添加反馈"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testCreateBudget() throws Exception {
        Budget budget = new Budget();
//        budget.setId(1);
        budget.setUserId(1);
        budget.setPlan(BigDecimal.valueOf(1000));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetBudget() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/budget/get")
                        .param("userId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetThisWeekBill() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisWeek")
                        .param("userId", "1")
                        .param("flag", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetThisMonthBill() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisMonth")
                        .param("userId", "1")
                        .param("flag", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetThisYearBill() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getThisYear")
                        .param("userId", "1")
                        .param("flag", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetRank() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bill/getRank")
                        .param("userId", "1")
                        .param("year", "2023")
                        .param("month", "12")
                        .param("flag", "false"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}