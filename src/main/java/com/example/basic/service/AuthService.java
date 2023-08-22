package com.example.basic.service;

import com.example.basic.dto.account.SignInDto;
import com.example.basic.dto.account.SignUpDto;
import com.example.basic.dto.common.ResponseDto;

public interface AuthService {

  ResponseDto signUp(SignUpDto signUpDto);

  ResponseDto signIn(SignInDto signInDto);

  ResponseDto generateAccessToken(String token);
}
