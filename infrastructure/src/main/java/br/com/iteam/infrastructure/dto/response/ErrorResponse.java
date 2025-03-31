package br.com.iteam.infrastructure.dto.response;

import lombok.Getter;
import java.util.List;

@Getter
public class ErrorResponse extends BaseResponse<Void> {
    private final String title;
    private final String detail;
    private final List<ErrorDetail> errors;

    public ErrorResponse(String title, int status, String detail, List<ErrorDetail> errors) {
        super(status, null);
        this.title = title;
        this.detail = detail;
        this.errors = errors;
    }

    public static ErrorResponse of(String title, int status, String detail, List<ErrorDetail> errors) {
        return new ErrorResponse(title, status, detail, errors);
    }

    public static ErrorResponse of(int status, String error) {
        return new ErrorResponse("Validation Failures", status, "One or more fields are invalid", List.of(new ErrorDetail("generic", error)));
    }
}
