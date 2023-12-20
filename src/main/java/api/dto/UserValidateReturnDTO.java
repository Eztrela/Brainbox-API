package api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserValidateReturnDTO(
        @NotEmpty boolean username,
        @NotEmpty boolean email
) {
}
