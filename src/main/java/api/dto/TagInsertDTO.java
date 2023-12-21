package api.dto;

import jakarta.validation.constraints.NotEmpty;

public record TagInsertDTO(
        @NotEmpty String title,
        @NotEmpty String color
) {
}
