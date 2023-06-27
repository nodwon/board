package com.example.board.repository;

import com.example.board.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(excerptProjection = UserAccountProjection.class)
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
