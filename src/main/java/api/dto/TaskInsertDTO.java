package api.dto;

import api.model.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record TaskInsertDTO(
        @NotEmpty String title,

        @NotEmpty String description,
        @NotEmpty String status,

        @NotNull Date datetimeDue,

        @NotNull Long priority,
        @NotNull Tag tag
) {
}