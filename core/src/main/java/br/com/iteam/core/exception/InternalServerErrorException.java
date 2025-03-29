package br.com.iteam.core.exception;

public class InternalServerErrorException extends Exception {

  public String code;

  public InternalServerErrorException(String message, String code) {
      super(message);
        this.code = code;
    }
}
