package com.example.board.controller;

import com.example.board.dto.UserAccountDto;
import com.example.board.dto.request.ArticleCommentRequest;
import com.example.board.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest){
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
                "no","pw","no@gmail.com", null, null
        )));

        return "redirect:/articles";
    }
    @PostMapping("/{articleId}/delete")
    public String deleteArticle(@PathVariable Long articleCommentId, @PathVariable String userId){
        articleCommentService.deleteArticleComment(articleCommentId, userId);

        return "redirect:/articles";
    }
}
