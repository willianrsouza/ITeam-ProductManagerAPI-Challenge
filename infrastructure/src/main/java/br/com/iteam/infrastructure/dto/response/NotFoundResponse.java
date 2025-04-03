package br.com.iteam.infrastructure.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response returned when a resource is not found")
public class NotFoundResponse {
    @Schema(example = "Resource Not Found.")
    private String title;

    @Schema(example = "404")
    private int status;

    @Schema(example = "Not found categories.")
    private String detail;
}
