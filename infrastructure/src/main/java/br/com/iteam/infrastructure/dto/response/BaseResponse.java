package br.com.iteam.infrastructure.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private final int status;
    private final T result;

    protected BaseResponse(int status, T result) {
        this.status = status;
        this.result = result;
    }
}

