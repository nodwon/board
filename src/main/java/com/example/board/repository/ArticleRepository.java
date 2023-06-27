package com.example.board.repository;

import com.example.board.domain.Article;
import com.example.board.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article>, // 기본적으로 Article에있는 기본 검사기능을 추가해줌
        QuerydslBinderCustomizer<QArticle> { // 부분검색을 추가하기 위해 binderCustomizer를 추가한다
    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);
    Page<Article> findByUserAccount_Hashtag(String hashtag, Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        // 일부검색을 위해 사용한다.
        bindings.excludeUnlistedProperties(true);  //선택적인 필드만 검색할수 있다.
        bindings.including(root.title, root.hashtag, root.createdAt, root.createdBy); // 원하는 기능추가
        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // 두개의 차이점은 생성 쿼리가 다름  like '{v}'//수동으로 설정할때
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // 대소문자 구분안하게 like '%{v}%'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); // 대소문자 구분안하게 like '%{v}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 날짜가 동일한지
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // 대소문자 구분안하게 like '%{v}%'
    }
}
