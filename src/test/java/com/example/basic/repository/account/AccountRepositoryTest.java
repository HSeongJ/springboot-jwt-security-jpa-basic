package com.example.basic.repository.account;

import com.example.basic.entity.account.AccountEntity;
import com.example.basic.entity.account.AccountRole;
import com.example.basic.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
@TestPropertySource(value = "/application-dev.properties")
class AccountRepositoryTest {

  @Autowired
  AccountRepository accountRepository;

  @Test
  public void test() {
    AccountEntity account = AccountEntity.builder()
      .id("test")
      .password("pass")
      .role(Set.of(AccountRole.USER))
      .build();

    accountRepository.save(account);

    Optional<AccountEntity> result = accountRepository.findAccountEntityById("test");

    if(result.isPresent()) {
      System.out.println(result.get());
    }
  }
}