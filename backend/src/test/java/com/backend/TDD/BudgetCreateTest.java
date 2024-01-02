package com.backend.TDD;

import com.backend.entity.Budget;
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
class BudgetCreateTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noBudget() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noUserId() throws Exception {
        Budget budget = new Budget();
        budget.setYearAndMonth("2024-1");
        budget.setPlan(BigDecimal.valueOf(100.2));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noExistUserId() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(116);
        budget.setYearAndMonth("2024-1");
        budget.setPlan(BigDecimal.valueOf(100.2));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void negativePlan() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setYearAndMonth("2024-1");
        budget.setPlan(BigDecimal.valueOf(-127.06));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noYearAndMonthInsert() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setPlan(BigDecimal.valueOf(127.06));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidYearAndMonth() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setYearAndMonth("2023/10");
        budget.setPlan(BigDecimal.valueOf(127.06));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void validYearAndMonthInsert() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setYearAndMonth("2024-1");
        budget.setPlan(BigDecimal.valueOf(127.06));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noYearAndMonthUpdate() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setPlan(BigDecimal.valueOf(127.06));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void validCase1() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setYearAndMonth("2023-10");
        budget.setPlan(BigDecimal.valueOf(1300));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void validCase2() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setPlan(BigDecimal.valueOf(1300));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase1() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(-11);
        budget.setYearAndMonth("2023-10");
        budget.setPlan(BigDecimal.valueOf(13));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase2() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setYearAndMonth("2023/10");
        budget.setPlan(BigDecimal.valueOf(13));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase3() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setYearAndMonth("1989-10");
        budget.setPlan(BigDecimal.valueOf(13));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase4() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setYearAndMonth("2051-10");
        budget.setPlan(BigDecimal.valueOf(13));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase5() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setYearAndMonth("2023-0");
        budget.setPlan(BigDecimal.valueOf(13));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase6() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setYearAndMonth("2023-13");
        budget.setPlan(BigDecimal.valueOf(13));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase7() throws Exception {
        Budget budget = new Budget();
        budget.setUserId(1);
        budget.setYearAndMonth("2023-10");
        budget.setPlan(BigDecimal.valueOf(-370.12));
        String jsonStr = Json.pretty(budget);
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }




}