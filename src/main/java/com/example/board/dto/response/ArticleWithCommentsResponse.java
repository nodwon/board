package com.example.board.dto.response;

import com.example.board.dto.ArticleDto;
import com.example.board.dto.ArticleWithCommentsDto;

import java.time.LocalDateTime;

public record ArticleWithCommentsResponse(
        Long id,
        String title,
        String content,
        String hashtags,
        //Set<String> hashtags,
        LocalDateTime createdAt,
        String email,
        String nickname
        //String userId
        //Set<ArticleCommentResponse> articleCommentsResponse
) {

    public static ArticleWithCommentsResponse of(Long id, String title, String content, String hashtags , LocalDateTime createdAt, String email, String nickname) {
        return new ArticleWithCommentsResponse(id, title, content, hashtags, createdAt, email, nickname);
    }
    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return new ArticleWithCommentsResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag()
//                dto.hashtagDtos().stream()
//                        .map(HashtagDto::hashtagName)
//                        .collect(Collectors.toUnmodifiableSet())
                ,
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname
                //dto.userAccountDto().userId()
             //   organizeChildComments(dto.articleCommentDtos())
        );
    }



//    private static Set<ArticleCommentResponse> organizeChildComments(Set<ArticleCommentDto> dtos) {
//        Map<Long, ArticleCommentResponse> map = dtos.stream()
//                .map(ArticleCommentResponse::from)
//                .collect(Collectors.toMap(ArticleCommentResponse::id, Function.identity()));
//
//        map.values().stream()
//                .filter(ArticleCommentResponse::hasParentComment)
//                .forEach(comment -> {
//                    ArticleCommentResponse parentComment = map.get(comment.parentCommentId());
//                    parentComment.childComments().add(comment);
//                });
//
//        return map.values().stream()
//                .filter(comment -> !comment.hasParentComment())
//                .collect(Collectors.toCollection(() ->
//                        new TreeSet<>(Comparator
//                                .comparing(ArticleCommentResponse::createdAt)
//                                .reversed()
//                                .thenComparingLong(ArticleCommentResponse::id)
//                        )
//                ));
//    }
}
