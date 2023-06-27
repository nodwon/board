package com.example.board.service;

import com.example.board.domain.Article;
import com.example.board.domain.type.SearchType;
import com.example.board.dto.ArticleDto;
import com.example.board.dto.ArticleUpdateDto;
import com.example.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;
@DisplayName("비지니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;

    /*
    * 1. 검색
    2. 각 게시글 페이지로 이동
    3. 페이지네이션
    4. 홈 버튼 -> 게시판 페이지로 리다이렉션
    5. 정렬기능
     */
    @DisplayName("게시글을 검색하면, 게시글 리스트르 반환한다.")
    @Test
    public void searchParmeter_articles() throws Exception {
        //given
        //when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");// 제목, 본문, ID, 닉네임, 해시테크
        //then
        assertThat(articles).isNotNull();
    }
    @DisplayName("게시글을 조회하면, 게시글울 반환한다.")
    @Test
    public void searchArticle() throws Exception {
        //given
        //when
        List<ArticleDto> articles = sut.searchArticle(1L);// 제목, 본문, ID, 닉네임, 해시테크
        //then
        assertThat(articles).isNotNull();
    }
}