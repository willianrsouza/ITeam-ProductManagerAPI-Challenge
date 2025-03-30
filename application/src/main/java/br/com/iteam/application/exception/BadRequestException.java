package br.com.iteam.application.exception;

public class BadRequestException extends Exception {

  public String code;

  public BadRequestException(String message, String code) {
        super(message);
        this.code = code;
    }
}
