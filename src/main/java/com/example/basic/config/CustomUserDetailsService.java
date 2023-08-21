package com.example.basic.config;

import com.example.basic.entity.account.AccountEntity;
import com.example.basic.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    AccountEntity account = accountRepository.findAccountEntityById(id).orElseThrow(
      () -> new UsernameNotFoundException("User not found with this id :" + id)
    );

    return CustomUserDetails.create(account);
  }

  public UserDetails loadUserByIdx(Long idx) {
    AccountEntity entity = accountRepository.findAccountEntityByIdx(idx).orElseThrow(
      () -> new UsernameNotFoundException("User not found with this idx :" + idx)
    );

    return CustomUserDetails.create(entity);
  }
}
