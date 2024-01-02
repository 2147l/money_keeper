package com.backend.TDD;

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

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserUpdateTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noUserId() throws Exception {
        User user = new User();
        user.setUsername("廖杰");
        user.setAvatar("http://dummyimage.com/100x100");
        user.setSex("男");
        user.setWechat("magna");
        user.setEmail("o.irgj@qq.com");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void noExistUserId() throws Exception {
        User user = new User();
        user.setId(65);
        user.setUsername("曾洋");
        user.setAvatar("src/assets/avatar/8.png");
        user.setSex("女");
        user.setWechat("hsvohubveey");
        user.setEmail("z.rnjbeeibm@yobfchei.com");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void invalidUsername() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Kevin@#");
        user.setAvatar("src/assets/avatar/8.png");
        user.setSex("女");
        user.setWechat("tkejklf");
        user.setEmail("f.wwemjbv@fdvksicx.tg");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void emptyUpdate() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("jj");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void onlyUpdateUsername() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Scott");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void onlyUpdateSexToMale() throws Exception {
        User user = new User();
        user.setId(1);
        user.setSex("男");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void onlyUpdateSexToFemale() throws Exception {
        User user = new User();
        user.setId(1);
        user.setSex("女");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void onlyUpdateInvalidSex() throws Exception {
        User user = new User();
        user.setId(1);
        user.setSex("沃尔玛购物袋");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void onlyUpdateValidEmail() throws Exception {
        User user = new User();
        user.setId(1);
        user.setEmail("w.gvlqlksor@iydfrcyhgr.nu");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void onlyUpdateInvalidEmail() throws Exception {
        User user = new User();
        user.setId(1);
        user.setEmail("e.wowik@vfz@pscmmmb.gp");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void ValidCase() throws Exception {
        User user = new User();
        user.setId(94);
        user.setUsername("Carol");
        user.setAvatar("src/assets/avatar/20.png");
        user.setSex("女");
        user.setWechat("hqwhmpxzrkdqvt");
        user.setEmail("u.ffrcgj@sekbl.io");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void inValidCase1() throws Exception {
        User user = new User();
        user.setId(-94);
        user.setUsername("Carol");
        user.setAvatar("src/assets/avatar/20.png");
        user.setSex("女");
        user.setWechat("hqwhmpxzrkdqvt");
        user.setEmail("u.ffrcgj@sekbl.io");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void inValidCase2() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Caroljose开心爆米花mikeShirley");
        user.setAvatar("src/assets/avatar/20.png");
        user.setSex("女");
        user.setWechat("hqwhmpxzrkdqvt");
        user.setEmail("u.ffrcgj@sekbl.io");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void inValidCase3() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("@coco");
        user.setAvatar("src/assets/avatar/20.png");
        user.setSex("女");
        user.setWechat("hqwhmpxzrkdqvt");
        user.setEmail("u.ffrcgj@sekbl.io");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void inValidCase4() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Cora");
        user.setAvatar("src/assets/avatar/20.png");
        user.setSex("外星人");
        user.setWechat("hqwhmpxzrkdqvt");
        user.setEmail("u.ffrcgj@sekbl.io");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void inValidCase5() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Cora");
        user.setAvatar("src/assets/avatar/20.png");
        user.setSex("女");
        user.setWechat("hqw_hmpxzrk_dwhmpx_zrkdqvtqvt");
        user.setEmail("u.ffrcgj@sekbl.io");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void inValidCase6() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Cora");
        user.setAvatar("src/assets/avatar/20.png");
        user.setSex("女");
        user.setWechat("hqw_hmp(}xzrk_dwt");
        user.setEmail("u.ffrcgj@sekbl.io");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void inValidCase7() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Cora");
        user.setAvatar("src/assets/avatar/20.png");
        user.setSex("女");
        user.setWechat("hqwhmpxzrkdwt");
        user.setEmail("@sekbl@.io");
        String jsonStr = Json.pretty(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}