package br.com.iteam.core.exception;

public class NotFoundException extends Exception {

  public String code;

  public NotFoundException(String message, String code) {
      super(message);
        this.code = code;
    }
}
