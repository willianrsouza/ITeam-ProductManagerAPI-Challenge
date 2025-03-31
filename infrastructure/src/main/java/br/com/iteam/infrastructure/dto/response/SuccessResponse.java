package br.com.iteam.infrastructure.dto.response;

public class SuccessResponse<T> extends BaseResponse<T> {
    private SuccessResponse(int status, T result) {
        super(status, result);
    }

    public static <T> SuccessResponse<T> of(int status, T result) {
        return new SuccessResponse<>(status, result);
    }
}
