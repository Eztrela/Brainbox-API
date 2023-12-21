package api.dto;

import api.model.Note;
import api.model.Tag;
import api.model.Task;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public record MemoryboxUpdateDTO(
        @NotEmpty String title,

        @NotNull List<Task> tasks,

        @NotNull List<Note> notes,
        @NotNull List<Tag> tags,

        @NotEmpty String banner
) {
}
