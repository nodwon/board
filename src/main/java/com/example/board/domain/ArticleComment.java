package com.example.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(name = "article_comment")
@Entity
public class ArticleComment extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false)
    @JoinColumn(name = "article_id")
    private Article article; // 게시글 id
    @Setter
    @JoinColumn(name = "user_id")
    @ManyToOne(optional = false)
    private UserAccount userAccount; // 유저 정보 (ID)
    @Setter
    @Column(updatable = false)
    private Long parentCommentId; // 부모 댓글 ID

    @ToString.Exclude
    @OrderBy("createdAt ASC")
    @OneToMany(mappedBy = "parentCommentId", cascade = CascadeType.ALL)
    private Set<ArticleComment> childComments = new LinkedHashSet<>();
    @Setter @Column(name = "content", length = 500) private String content; // 본문

    protected ArticleComment() {

    }
    private ArticleComment(Article article,UserAccount userAccount, String content) {
        this.userAccount = userAccount;
        this.article = article;
        this.content = content;
    }
    public static ArticleComment of(Article article, UserAccount userAccount, String content) {
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

    public void addChildComment(ArticleComment articleComment) {
    }
}
