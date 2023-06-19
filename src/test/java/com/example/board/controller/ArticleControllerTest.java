package com.example.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("view 컨트롤러 - 게시글")
@WebMvcTest
class ArticleControllerTest {

    private final MockMvc mvc;


    ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][Get] 게시글 리스트 = 페이지 정상 호출")
    @Test
    public void requestview() throws Exception {
        //given

        //when
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles")); // 이맵에 articles가 있는지

        //then
    }
    @DisplayName("[view][Get] 게시글 상세페이지 = 페이지 정상 호출")
    @Test
    public void requestviewdetail() throws Exception {
        //given

        //when
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles")); // 이맵에 articles가 있는지

        //then
    }
    @DisplayName("[view][Get] 게시글 검색전용 페이지 = 페이지 정상 호출")
    @Test
    public void searcharticle() throws Exception {
        //given

        //when
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles")); // 이맵에 articles가 있는지

        //then
    }
    @DisplayName("[view][Get] 게시글 해시태그 검색전용 페이지 = 페이지 정상 호출")
    @Test
    public void searchhastagarticle() throws Exception {
        //given

        //when
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles")); // 이맵에 articles가 있는지

        //then
    }
}