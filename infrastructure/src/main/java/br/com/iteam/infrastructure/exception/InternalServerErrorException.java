package br.com.iteam.infrastructure.exception;

public class InternalServerErrorException extends Exception {

  public String code;

  public InternalServerErrorException(String message, String code) {
      super(message);
        this.code = code;
    }
}
