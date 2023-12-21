package api.dto;

import api.model.Tag;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record TaskInsertDTO(
        @NotEmpty String title,

        @NotEmpty String description,
        @NotEmpty String status,

        @NotEmpty Date datetimeDue,

        @NotEmpty Long priority,
        @NotEmpty Tag tag
) {
}