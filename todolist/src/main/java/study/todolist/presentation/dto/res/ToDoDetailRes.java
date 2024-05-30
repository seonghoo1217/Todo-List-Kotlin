package study.todolist.presentation.dto.res;

import study.todolist.domain.Category;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.UUID;

public record ToDoDetailRes(UUID uuid,
                            String title,
                            String contents,
                            Category category,
                            ZonedDateTime postTime,
                            DayOfWeek dayOfWeek,
                            int viewer) {
}
