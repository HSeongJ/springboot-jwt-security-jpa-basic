package com.example.basic.controller;

import com.example.basic.dto.common.ResponseDto;
import com.example.basic.util.ResponseCode;
import com.example.basic.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

  @GetMapping("/test")
  public ResponseDto test(@RequestBody Map<String, Object> param) {
    return ResponseUtil.SUCCESS(ResponseCode.ACCOUNT_SIGN_IN_SUCCESS, param);
  }
}
