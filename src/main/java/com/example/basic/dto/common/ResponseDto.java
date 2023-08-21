package com.example.basic.dto.common;

import lombok.Getter;

@Getter
public class ResponseDto<T> {

  public ResponseDto(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public ResponseDto(int status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  private final int status;
  private final String message;
  private T data;
}
