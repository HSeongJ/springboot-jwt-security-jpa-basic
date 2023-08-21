package com.example.basic.exception;

import com.example.basic.dto.common.ResponseDto;
import com.example.basic.util.MasterConstant;
import com.example.basic.util.ResponseCode;
import com.example.basic.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Log4j2
@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(value = CustomException.class)
  public ResponseDto customInternalException(CustomException e) {
    return ResponseUtil.ERROR(e.getResponseCode());
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseDto validationException(MethodArgumentNotValidException e) {
    String column = e.getFieldError().getDefaultMessage();

    String errorCode = e.getFieldError().getCode();
    log.error(errorCode);

    if(Objects.equals(errorCode, MasterConstant.VALID_NOT_BLACK)) return ResponseUtil.ERROR(ResponseCode.REQUIRE_DATA_NULL, column);
    if(Objects.equals(errorCode, MasterConstant.VALID_LENGTH))    return ResponseUtil.ERROR(ResponseCode.REQUEST_DATA_LENGTH_INVALID, column);

    return ResponseUtil.ERROR(ResponseCode.REQUEST_DATA_ERROR, column);
  }
}
