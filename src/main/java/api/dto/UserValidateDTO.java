package api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserValidateDTO(
        @NotEmpty String username,
        @NotEmpty @Email String email
) {
}
