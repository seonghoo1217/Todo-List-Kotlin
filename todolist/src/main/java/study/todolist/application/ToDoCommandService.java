package study.todolist.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.todolist.domain.Category;
import study.todolist.domain.Member;
import study.todolist.domain.ToDo;
import study.todolist.domain.exception.ToDoNotFoundException;
import study.todolist.domain.repository.ToDoRepository;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoCommandService {

    private final ToDoRepository toDoRepository;
    public static final Long DEFAULT_ID = 1L;

    public UUID createToDo(String title, String contents, Category category, ZonedDateTime postTime) {
        ToDo toDo = toDoRepository.save(DEFAULT_ID, new ToDo(UUID.randomUUID(), title, contents, category, postTime, 0, new Member()));

        return toDo.getUuid();
    }

    public void deleteToDoByUuid(UUID uuid) {
        ToDo toDo = toDoRepository.findByUuid(uuid).orElseThrow(ToDoNotFoundException::new);

        toDoRepository.deleteByUuid(toDo.getUuid());
    }
}
