package com.example.board.repository;

import com.example.board.domain.UserAccount;
import com.example.board.domain.projection.UserAccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource(excerptProjection = UserAccountProjection.class)
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
