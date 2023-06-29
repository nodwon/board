package com.example.board.controller;

import com.example.board.config.SecurityConfig;
import com.example.board.dto.ArticleDto;
import com.example.board.dto.ArticleWithCommentsDto;
import com.example.board.dto.UserAccountDto;
import com.example.board.service.ArticleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("view 컨트롤러 - 게시글")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    private final MockMvc mvc;

    @MockBean // 테스트에서 배제하기위해 입출력을 보기위해
    private ArticleService articleService; //필드 주입

    ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    } //생성자주입
    @DisplayName("[view][Get] 게시글 리스트 = 페이지 정상 호출")
    @Test
    public void requestview() throws Exception {
        //given
        given(articleService.searchArticles(eq(null), eq(null), any())).willReturn(Page.empty());        //when
        // When & Then

        mvc.perform(get("/articles"))
                .andExpect(status().isOk()) // 200 으로 떨어져야하고
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // text.ht
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles")); // 이맵에 articles가 있는지

        //then
        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));

    }
    @Disabled("구현중")
    @DisplayName("[view][Get] 게시글 상세페이지 = 페이지 정상 호출")
    @Test
    public void requestviewdetail() throws Exception {
        //given
        Long articleId =1L;
        //given(articleService.getArticle(articleId)).willReturn(createArticleWithCommentsDto());
        //when
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail")) // 이맵에 articles가 있는지
                .andExpect(model().attributeExists("article")) // 이맵에 articles가 있는지
                .andExpect(model().attributeExists("articleComments")); // 이맵에 articles가 있는지

        //then
    }

    @Disabled("구현중")

    @DisplayName("[view][Get] 게시글 검색전용 페이지 = 페이지 정상 호출")
    @Test
    public void searcharticle() throws Exception {
        //given

        //when
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search"))
                .andExpect(model().attributeExists("article")); // 이맵에 articles가 있는지

        //then
    }
    @Disabled("구현중")
    @DisplayName("[view][Get] 게시글 해시태그 검색전용 페이지 = 페이지 정상 호출")
    @Test
    public void searchhastagarticle() throws Exception {
        //given

        //when
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/hashtag"))
                .andExpect(model().attributeExists("article")); // 이맵에 articles가 있는지

        //then
    }
    private ArticleDto createArticleDto() {
        return ArticleDto.of(
                createUserAccountDto(),
                "title",
                "content",
               "java"
               // Set.of(HashtagDto.of("java"))
        );
    }

    private ArticleWithCommentsDto createArticleWithCommentsDto() {
        return ArticleWithCommentsDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "java",
                //Set.of(HashtagDto.of("java")),
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "uno",
                "pw",
                "uno@mail.com",
                "Uno",
                "memo",
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }
}