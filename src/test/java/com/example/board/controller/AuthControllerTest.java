package com.example.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@DisplayName("view 컨트롤러 - 로그인")
@WebMvcTest()
public class AuthControllerTest {

    private final MockMvc mvc;

    public AuthControllerTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }
    @DisplayName("[view][Get] 로그인페이지 - 정상호출")
    @Test
    public void loginpage() throws Exception {
        //given

        //when
        mvc.perform(get("/login"))
                .andExpect(status().isOk()) // 200 으로 떨어져야하고
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)); // text.ht

        //then
    }
}
