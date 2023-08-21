package com.example.basic.serviceImpl;

import com.example.basic.config.JwtTokenProvider;
import com.example.basic.dto.account.SignInDto;
import com.example.basic.dto.account.SignUpDto;
import com.example.basic.dto.common.ResponseDto;
import com.example.basic.dto.common.TokenInfoDto;
import com.example.basic.entity.account.AccountEntity;
import com.example.basic.entity.account.AccountRole;
import com.example.basic.exception.CustomException;
import com.example.basic.repository.AccountRepository;
import com.example.basic.service.AccountService;
import com.example.basic.util.ResponseCode;
import com.example.basic.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  @Override
  public ResponseDto signUp(SignUpDto signUpDto) {
    try {
      AccountEntity account = AccountEntity
        .builder()
        .id(signUpDto.getId())
        .password(passwordEncoder.encode(signUpDto.getPassword()))
        .role(AccountRole.USER.name())
        .build();

      accountRepository.save(account);
    } catch (Exception e) {
      throw new CustomException(ResponseCode.ACCOUNT_SIGN_UP_FAIL);
    }

    return ResponseUtil.SUCCESS(ResponseCode.ACCOUNT_SIGN_UP_SUCCESS, signUpDto);
  }

  @Override
  public ResponseDto signIn(SignInDto signInDto) {
    try {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInDto.getId(), signInDto.getPassword());
      Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

      SecurityContextHolder.getContext().setAuthentication(authentication);
      TokenInfoDto jwt = jwtTokenProvider.generateToken(authentication);

      return ResponseUtil.SUCCESS(ResponseCode.ACCOUNT_SIGN_IN_SUCCESS, jwt);
    } catch (Exception e) {
      return ResponseUtil.ERROR(ResponseCode.ACCOUNT_SIGN_IN_FAIL);
    }
  }
}
