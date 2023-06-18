package com.example.board.repository;

import com.example.board.config.JpaConfig;
import com.example.board.domain.Article;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private  final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
                             @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    public void select () throws Exception {
        //given

        //when
        List<Article> articles = articleRepository.findAll();
        //then
        assertThat(articles).isNotNull()
                .hasSize(0);
    }
    @DisplayName("insert 테스트")
    @Test
    public void insert () throws Exception {
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousCount = articleRepository.count();

        //when
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));
        //then
        assertThat(articleRepository.count()).isEqualTo(previousCount +1);
    }
    @DisplayName("update 테스트")
    @Test
    public void update () throws Exception {
        //give
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);
        long previousCount = articleRepository.count();

        //when
        Article savedArticle = articleRepository.save(article);
        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updatedHashtag);
    }
    @DisplayName("Delete 테스트")
    @Test
    public void Delete () throws Exception {
        //give
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        long deletedCommentSize = article.getArticleComments().size();


        //when
        articleRepository.delete(article);
        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommentSize);
    }

}