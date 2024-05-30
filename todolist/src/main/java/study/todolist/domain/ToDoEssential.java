package study.todolist.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ToDoEssential {
    private String title;

    private String contents;

    private Category category;

    private ZonedDateTime postTime;

    private DayOfWeek dayOfWeek;
}
