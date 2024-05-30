package study.todolist.presentation.dto.req;

import jakarta.validation.constraints.NotBlank;
import study.todolist.domain.Category;
import study.todolist.global.annotation.MinutePreciseUTC;

import java.time.ZonedDateTime;

public record CreateToDoReq(
        @NotBlank String title,
        String contents,
        Category category,
        @MinutePreciseUTC ZonedDateTime postTime) {
}
