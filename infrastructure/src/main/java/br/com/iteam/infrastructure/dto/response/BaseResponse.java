package br.com.iteam.infrastructure.dto.response;

public class BaseResponse<T> {
    private Boolean success;
    private String message;
    private T result;
    private ErrorResponse error;
}
