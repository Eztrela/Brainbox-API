package api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserAuthDTO(
        @NotEmpty String username,
        @NotEmpty @Size(min = 6, max = 24) String password
) {
}