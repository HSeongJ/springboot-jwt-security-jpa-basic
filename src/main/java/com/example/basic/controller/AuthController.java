package com.example.basic.controller;

import com.example.basic.dto.account.SignInDto;
import com.example.basic.dto.account.SignUpDto;
import com.example.basic.dto.common.ResponseDto;
import com.example.basic.service.AuthService;
import com.example.basic.util.MasterMethod;
import jakarta.servlet.http.HttpServletRequest;
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

  private final AuthService authService;

  @PostMapping(value = "/signUp")
  public ResponseDto signUp(@Valid @RequestBody SignUpDto signUpDto) {
    return authService.signUp(signUpDto);
  }

  @PostMapping(value = "/signIn")
  public ResponseDto signIn(@Valid @RequestBody SignInDto signInDto) {
    return authService.signIn(signInDto);
  }

  @PostMapping(value = "/refresh")
  public ResponseDto generateAccessToken(HttpServletRequest request) {
    String token = MasterMethod.getToken(request);

    return authService.generateAccessToken(token);
  }
}
