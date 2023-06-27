package com.example.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Id;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(name = "user_account")
@Entity
public class UserAccount extends AuditingFields{
    @Id
    @Column(length = 50, name = "user_id")
    private String userId;

    @Setter
    @Column(nullable = false,name="user_password") private String userPassword;

    @Setter @Column(length = 100 ,name="email", unique = true) private String email;
    @Setter @Column(length = 100, name="nickname") private String nickname;

    @Setter @Column(name="memo") private String memo;


    protected UserAccount() {}

    private UserAccount(String userId, String userPassword, String email, String nickname, String memo, String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo) {
        return UserAccount.of(userId, userPassword, email, nickname, memo, null);
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo, String createdBy) {
        return new UserAccount(userId, userPassword, email, nickname, memo, createdBy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return this.getUserId() != null && this.getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId());
    }
}
