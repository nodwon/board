package com.example.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import jakarta.persistence.*;
import java.util.Objects;
@Getter
@ToString
@Table(name = "article_comment")
@Entity
public class ArticleComment extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) private Article article; // 게시글 id
    @Setter
    @JoinColumn(name = "user_id")
    @ManyToOne(optional = false)
    private UserAccount userAccount; // 유저 정보 (ID)
    @Setter @Column(name = "content", length = 500) private String content; // 본문

    protected ArticleComment() {

    }
    private ArticleComment(Article article,UserAccount userAccount, String content) {
        this.userAccount = userAccount;
        this.article = article;
        this.content = content;
    }
    protected static ArticleComment of(Article article,UserAccount userAccount, String content) {
        return new ArticleComment(article,userAccount, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
