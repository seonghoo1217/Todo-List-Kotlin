package study.todolist.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.todolist.application.ToDoCommandService;
import study.todolist.application.ToDoQueryService;
import study.todolist.global.dto.ResponseEnvelope;
import study.todolist.presentation.dto.req.CreateToDoReq;
import study.todolist.presentation.dto.res.ToDoDetailRes;
import study.todolist.presentation.dto.res.ToDoUuidRes;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoCommandService toDoCommandService;

    private final ToDoQueryService toDoQueryService;

    @PostMapping
    public ResponseEnvelope<ToDoUuidRes> createToDo(@RequestBody CreateToDoReq createToDoReq) {
        UUID uuid = toDoCommandService.createToDo(createToDoReq.title(), createToDoReq.contents(), createToDoReq.category(), createToDoReq.postTime());

        return ResponseEnvelope.of(new ToDoUuidRes(uuid));
    }

    @GetMapping("/{uuid}")
    public ResponseEnvelope<ToDoDetailRes> findToDoById(@PathVariable UUID uuid) {
        ToDoDetailRes toDoDetailRes = toDoQueryService.findToDoByUuid(uuid);

        return ResponseEnvelope.of(toDoDetailRes);
    }

    @GetMapping("/me")
    public ResponseEnvelope<List<ToDoDetailRes>> findAllToDo() {
        List<ToDoDetailRes> todoList = toDoQueryService.findAllToDo();

        return ResponseEnvelope.of(todoList);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEnvelope<?> deleteToDoByUuid(@PathVariable UUID uuid) {
        toDoCommandService.deleteToDoByUuid(uuid);

        return ResponseEnvelope.of(null);
    }
}
