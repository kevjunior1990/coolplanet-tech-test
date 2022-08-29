package com.example.coolplanetdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(TaskCalculateCurrentAverageTimeException.class)
  public ResponseEntity<String> handleTaskCalculateAverageException(
      TaskCalculateCurrentAverageTimeException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TaskSaveException.class)
  public ResponseEntity<String> handleTaskSaveException(
      TaskSaveException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
