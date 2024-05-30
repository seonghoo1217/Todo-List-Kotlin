package study.todolist.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo {

    private UUID uuid;

    private ToDoEssential toDoEssential;

    private int viewer;

    private Member member;

    public ToDo(UUID uuid, String title, String contents, Category category, ZonedDateTime postTime, int viewer, Member member) {
        this.uuid = uuid;
        this.toDoEssential = new ToDoEssential(title, contents, category, postTime, postTime.getDayOfWeek());
        this.viewer = viewer;
        this.member = member;
    }
}
