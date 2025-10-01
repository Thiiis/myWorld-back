package com.example.demo.exception;

public class ResourceNotFoundeException extends RuntimeException {
  public ResourceNotFoundeException() {}
  public ResourceNotFoundeException(String message) {
    super(message);
  }
}
