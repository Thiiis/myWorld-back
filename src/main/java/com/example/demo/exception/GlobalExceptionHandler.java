package com.example.demo.exception;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException e) {
    Map<String, String> map = new HashMap<>();
    map.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<Map<String, String>> handleIllegalState(IllegalStateException e) {
    Map<String, String> map = new HashMap<>();
    map.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException e) {
    Map<String, Object> map = new HashMap<>();
    map.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(map);
  }

  @ExceptionHandler(ResourceNotFoundeException.class)
  public ResponseEntity<Map<String, Object>> handleResourceNotFoundeException(ResourceNotFoundeException e) {
    Map<String, Object> map = new HashMap<>();
    map.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
  }

}
