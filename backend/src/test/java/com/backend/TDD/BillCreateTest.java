package com.backend.TDD;

import com.backend.entity.Bill;
import com.backend.entity.Budget;
import io.swagger.v3.core.util.Json;
import org.assertj.core.api.BigDecimalAssert;
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
class BillCreateTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noBill() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noUserId() throws Exception {
        Bill bill = new Bill();
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(7.5));
        bill.setDate("2023-11-05");
        bill.setNote("早餐");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noExistUserId() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(61);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(7.5));
        bill.setDate("2023-11-05");
        bill.setNote("早餐");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noAmount() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setDate("2023-11-05");
        bill.setNote("早餐");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void AmountEqualsZero() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(0));
        bill.setDate("2023-11-05");
        bill.setNote("早餐");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noDate() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(-7.5));
        bill.setNote("早餐");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void typeMismatchAmount() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(7.5));
        bill.setNote("早餐");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void errorIncomeCategory() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(true);
        bill.setAmount(BigDecimal.valueOf(7.5));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void errorExpenseCategory() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(15);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(-7.5));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidDateFormat() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(-7.5));
        bill.setDate("2023-11/05");
        bill.setNote("早餐");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void validDate() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(-7.5));
        bill.setDate("2023-11-05");
        bill.setNote("早餐");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidDate() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(-7.5));
        bill.setDate("2023-2-29");
        bill.setNote("早餐");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void validCase1() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(7.5));
        bill.setDate("2023-11-05");
        bill.setNote("早餐");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void validCase2() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(15);
        bill.setType(true);
        bill.setAmount(BigDecimal.valueOf(133.66));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase1() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(-36);
        bill.setCategoryId(11);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(7.5));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase2() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(-7);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(7.5));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase3() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(29);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(7.5));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase4() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(0);
        bill.setType(true);
        bill.setAmount(BigDecimal.valueOf(71.5));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase5() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(17);
        bill.setType(true);
        bill.setAmount(BigDecimal.valueOf(71.5));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase6() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(0));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase7() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(12345678910.2));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase8() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(33.123));
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase9() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(33.1));
        bill.setDate("2023/11-5");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidCase10() throws Exception {
        Bill bill = new Bill();
        bill.setUserId(1);
        bill.setCategoryId(1);
        bill.setType(false);
        bill.setAmount(BigDecimal.valueOf(33.1));
        bill.setDate("2023-2-29");
        String jsonStr = Json.pretty(bill);
        mockMvc.perform(MockMvcRequestBuilders.post("/bill/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }









}