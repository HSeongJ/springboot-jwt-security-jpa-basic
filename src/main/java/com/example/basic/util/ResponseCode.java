package com.example.basic.util;

public enum ResponseCode {

  ACCOUNT_SIGN_UP_SUCCESS(200, "회원가입에 성공했습니다."),

  ACCOUNT_SIGN_UP_FAIL(500, "회원가입에 실패했습니다."),
  ACCOUNT_SIGN_IN_SUCCESS(200, "로그인에 성공했습니다."),
  ACCOUNT_SIGN_IN_FAIL(500, "로그인에 실패했습니다."),

  REQUIRE_DATA_NULL(500, "[ $ ] 필수 입력값입니다."),
  REQUEST_DATA_LENGTH_INVALID(500, "[ $ ] 유효하지 않은 길이입니다."),
  REQUEST_DATA_ERROR(500, "유효하지 않은 값입니다."),

  REQUEST_SUCCESS(200, "Success"),
  ACCESS_DENIED(403, "Invalid JWT signature");

  private final int status;
  private final String msg;

  ResponseCode(int status, String msg) {
    this.status = status;
    this.msg = msg;
  }

  public int getStatus() {
    return this.status;
  }

  public String getMsg() {
    return this.msg;
  }

  public String getMsg(String customStr) {
    String str = this.msg;

    str = str.replace("$", customStr);

    return str;
  }
  public String getMsg(String[] customStr) {
    String str = this.msg;

    for(int i = 0; i < customStr.length; i++) {
      str = str.replace("$", customStr[i]);
    }

    return str;
  }
}
