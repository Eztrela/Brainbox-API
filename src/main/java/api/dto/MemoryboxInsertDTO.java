package api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MemoryboxInsertDTO(
        @NotEmpty String title,
        @NotEmpty String banner,

        @NotNull Long userId

) {
}
