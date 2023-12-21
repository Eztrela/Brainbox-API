package api.dto;

import jakarta.validation.constraints.NotEmpty;

public record NoteInsertDTO(@NotEmpty String content) {

}
