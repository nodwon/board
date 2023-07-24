package com.example.board.repository;

import com.example.board.domain.ArticleComment;
import com.example.board.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment>{
    // 추가적인 메서드 정의 가능
    List<ArticleComment> findByArticle_Id(Long articleId);
    void deleteByIdAndUserAccount_UserId(Long articleCommentId, String userId);
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root){
        // 일부검색을 위해 사용한다.
        bindings.excludeUnlistedProperties(true);  //선택적인 필드만 검색할수 있다.
        bindings.including(root.content,root.createdAt, root.createdBy); // 원하는 기능추가
        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // 두개의 차이점은 생성 쿼리가 다름  like '{v}'//수동으로 설정할때
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // 대소문자 구분안하게 like '%{v}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 날짜가 동일한지
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // 대소문자 구분안하게 like '%{v}%'
    }

}
