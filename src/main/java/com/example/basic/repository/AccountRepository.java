package com.example.basic.repository;

import com.example.basic.entity.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

  Optional<AccountEntity> findAccountEntityById(String id);

  Optional<AccountEntity> findAccountEntityByIdx(Long idx);
}
