package api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserInsertDTO(
        @NotEmpty String username,
        @NotEmpty @Email String email,
        @NotEmpty @Size(min = 6, max = 24) String password
        ) {
}
