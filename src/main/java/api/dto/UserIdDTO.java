package api.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserIdDTO(
        @NotEmpty String id
) {
}