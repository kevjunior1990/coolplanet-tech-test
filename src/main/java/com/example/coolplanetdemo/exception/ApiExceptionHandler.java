package com.example.coolplanetdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(TaskCalculateAverageException.class)
  public ResponseEntity<String> handleTaskCalculateAverageException(
      TaskCalculateAverageException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.FAILED_DEPENDENCY);
  }

  @ExceptionHandler(TaskSaveException.class)
  public ResponseEntity<String> handleTaskSaveException(
      TaskSaveException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.FAILED_DEPENDENCY);
  }
}
