package com.example.board.service;

import com.example.board.domain.constant.SearchType;
import com.example.board.dto.ArticleDto;
import com.example.board.repository.ArticleRepository;
import com.example.board.repository.HashtagRepository;
import com.example.board.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;
@DisplayName("비지니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;

    @Mock private HashtagService hashtagService;
    @Mock private ArticleRepository articleRepository;
    @Mock private UserAccountRepository userAccountRepository;
    @Mock private HashtagRepository hashtagRepository;

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
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword", Pageable.unpaged());// 제목, 본문, ID, 닉네임, 해시테크
        //then
        assertThat(articles).isNotNull();
    }
    @DisplayName("검색어 없이 게시글을 해시태그 검색하면, 빈페이지로 반환한다.")
    @Test
    public void hastagsSearchBlank() throws Exception {
        // Given
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);
    }
    @DisplayName("게시글을 조회하면, 게시글울 반환한다.")
    @Test
    public void searchArticle() throws Exception {
        // Given
        SearchType searchType = SearchType.TITLE;
        String searchKeyword = "title";
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findByTitleContaining(searchKeyword, pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(searchType, searchKeyword, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findByTitleContaining(searchKeyword, pageable);
    }
    @DisplayName("게시글을 조회하면, 게시글 수울 반환한다.")
    @Test
    public void Articlecount() throws Exception {
        // Given
       long expected = 0l;
       given(articleRepository.count()).willReturn(expected);

       long actual = sut.getArticleCount();

       assertThat(actual).isEqualTo(expected);
       then(articleRepository).should().count();
    }

    @DisplayName("해시태그 조회하면, 해시태그 리스트 반환한다.")
    @Test
    public void returnhastag() throws Exception {
        // Given
        List<String> expectedHashtags = List.of("#java", "#spring","#boot");
        given(articleRepository.findAllDistinctHashtags()).willReturn(expectedHashtags);

        List<String> actualHashtags = sut.getHashtags();

        assertThat(actualHashtags).isEqualTo(expectedHashtags);
        then(articleRepository).should().findAllDistinctHashtags();
    }

    @DisplayName("검색어와 함께 게시글을 검색하면, 게시글 페이지를 반환한다.")
    @Test
    void givenNoSearchParameters_whenSearchingArticlesViaHashtag_thenReturnsEmptyPage() {
        // Given
        Pageable pageable = Pageable.ofSize(20);

        // When
        Page<ArticleDto> articles = sut.searchArticlesViaHashtag(null, pageable);

        // Then
        assertThat(articles).isEqualTo(Page.empty(pageable));
        then(hashtagRepository).shouldHaveNoInteractions();
        then(articleRepository).shouldHaveNoInteractions();
    }
//    @DisplayName("없는 해시태그를 검색하면, 빈 페이지를 반환한다.")
//    @Test
//    void givenNonexistentHashtag_whenSearchingArticlesViaHashtag_thenReturnsEmptyPage() {
//        // Given
//        String hashtagName = "난 없지롱";
//        Pageable pageable = Pageable.ofSize(20);
//        given(articleRepository.findByHashtagNames(List.of(hashtagName), pageable)).willReturn(new PageImpl<>(List.of(), pageable, 0));
//
//        // When
//        Page<ArticleDto> articles = sut.searchArticlesViaHashtag(hashtagName, pageable);
//
//        // Then
//        assertThat(articles).isEqualTo(Page.empty(pageable));
//        then(articleRepository).should().findByHashtagNames(List.of(hashtagName), pageable);
//    }
//
//    @DisplayName("게시글을 해시태그 검색하면, 게시글 페이지를 반환한다.")
//    @Test
//    void givenHashtag_whenSearchingArticlesViaHashtag_thenReturnsArticlesPage() {
//        // Given
//        String hashtagName = "java";
//        Pageable pageable = Pageable.ofSize(20);
//        Article expectedArticle = createArticle();
//        given(articleRepository.findByHashtagNames(List.of(hashtagName), pageable)).willReturn(new PageImpl<>(List.of(expectedArticle), pageable, 1));
//
//        // When
//        Page<ArticleDto> articles = sut.searchArticlesViaHashtag(hashtagName, pageable);
//
//        // Then
//        assertThat(articles).isEqualTo(new PageImpl<>(List.of(ArticleDto.from(expectedArticle)), pageable, 1));
//        then(articleRepository).should().findByHashtagNames(List.of(hashtagName), pageable);
//    }
}