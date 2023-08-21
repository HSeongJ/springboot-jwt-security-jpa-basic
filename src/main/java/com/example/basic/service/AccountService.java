package com.example.basic.service;

import com.example.basic.dto.account.SignInDto;
import com.example.basic.dto.account.SignUpDto;
import com.example.basic.dto.common.ResponseDto;

public interface AccountService {

  ResponseDto signUp(SignUpDto signUpDto);

  ResponseDto signIn(SignInDto signInDto);
}
