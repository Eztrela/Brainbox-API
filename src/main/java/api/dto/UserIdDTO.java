package api.dto;

import jakarta.validation.constraints.NotNull;

public record UserIdDTO(
        @NotNull Long id
) {
}