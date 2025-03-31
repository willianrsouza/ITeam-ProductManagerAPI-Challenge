package br.com.iteam.infrastructure.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {
    private int status;
    private Boolean success;
    private T result;
}
