package br.com.iteam.infrastructure.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response returned when access to a resource is forbidden")
public class ForbiddenResponse {

    @Schema(example = "2025-04-03T11:25:57.972+00:00", description = "Timestamp when the error occurred")
    private LocalDateTime timestamp;

    @Schema(example = "403", description = "HTTP status code")
    private int status;

    @Schema(example = "Forbidden", description = "Error type")
    private String error;

    @Schema(example = "Forbidden", description = "Error message")
    private String message;
}

