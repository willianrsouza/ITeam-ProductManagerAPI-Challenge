package br.com.iteam.infrastructure.exception;

public class NotFoundException extends Exception {

  public String code;

  public NotFoundException(String message, String code) {
      super(message);
        this.code = code;
    }
}
