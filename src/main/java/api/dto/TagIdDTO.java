package api.dto;

import jakarta.validation.constraints.NotNull;

public record TagIdDTO(
        @NotNull Long id
) {
}