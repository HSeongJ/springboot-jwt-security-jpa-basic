package com.example.basic.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class SignInDto {

  private String id;
  private String password;
}
