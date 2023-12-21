package api.dto;

import api.model.Note;
import api.model.Tag;
import api.model.Task;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public record MemoryboxUpdateDTO(
        @NotEmpty String title,
        @NotEmpty Date datetimeCreated,

        @NotEmpty List<Task> tasks,

        @NotEmpty List<Note> notes,
        @NotEmpty List<Tag> tags,

        @NotEmpty String banner
) {
}
