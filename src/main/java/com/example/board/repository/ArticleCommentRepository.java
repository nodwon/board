package com.example.board.repository;

import com.example.board.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
    // 추가적인 메서드 정의 가능
}
