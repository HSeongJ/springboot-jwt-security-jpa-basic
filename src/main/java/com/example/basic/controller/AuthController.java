package com.example.basic.controller;

import com.example.basic.dto.account.SignInDto;
import com.example.basic.dto.account.SignUpDto;
import com.example.basic.dto.common.ResponseDto;
import com.example.basic.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AccountService accountService;

  @PostMapping(value = "/signUp")
  public ResponseDto signUp(@Valid @RequestBody SignUpDto signUpDto) {
    return accountService.signUp(signUpDto);
  }

  @PostMapping(value = "/signIn")
  public ResponseDto signIn(@Valid @RequestBody SignInDto signInDto) {
    return accountService.signIn(signInDto);
  }
}
