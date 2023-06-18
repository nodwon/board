package com.example.board.repository;

import com.example.board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
