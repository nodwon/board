package com.example.board.service;

import java.time.LocalDateTime;

record ArticleCommentDto(
        Long id,
        Long articleId,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleCommentDto of(Long articleId, String content) {
        return ArticleCommentDto.of(articleId, content);
    }
}


