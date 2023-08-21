package com.example.basic.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfoDto {

  private String accessToken;
  private String refreshToken;
}
