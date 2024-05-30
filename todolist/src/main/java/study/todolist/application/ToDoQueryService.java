package study.todolist.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.todolist.domain.ToDo;
import study.todolist.domain.exception.ToDoNotFoundException;
import study.todolist.domain.repository.ToDoRepository;
import study.todolist.presentation.dto.res.ToDoDetailRes;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoQueryService {

    private final ToDoRepository toDoRepository;


    public ToDoDetailRes findToDoByUuid(UUID uuid) {
        ToDo todo = toDoRepository.findByUuid(uuid).orElseThrow(ToDoNotFoundException::new);

        return new ToDoDetailRes(uuid,
                todo.getToDoEssential().getTitle(),
                todo.getToDoEssential().getContents(),
                todo.getToDoEssential().getCategory(),
                todo.getToDoEssential().getPostTime(),
                todo.getToDoEssential().getDayOfWeek(),
                todo.getViewer());
    }

    public List<ToDoDetailRes> findAllToDo() {
        List<ToDo> toDoList = toDoRepository.findAll();

        return toDoList.stream().map(toDo ->
                new ToDoDetailRes(
                        toDo.getUuid(),
                        toDo.getToDoEssential().getTitle(),
                        toDo.getToDoEssential().getContents(),
                        toDo.getToDoEssential().getCategory(),
                        toDo.getToDoEssential().getPostTime(),
                        toDo.getToDoEssential().getDayOfWeek(),
                        toDo.getViewer()
                )).toList();
    }
}
