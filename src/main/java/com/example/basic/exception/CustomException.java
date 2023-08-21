package com.example.basic.exception;

import com.example.basic.util.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
  private final ResponseCode responseCode;
}
