package study.todolist.domain.todo.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.todolist.domain.todo.TodoRepository;
import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;
import study.todolist.domain.todo.entity.Todo;
import study.todolist.domain.todo.entity.TodoTask;
import study.todolist.domain.todo.exception.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public ViewSingleResponse createTodo(TodoRequest request) {
        TodoTask todoTask = TodoTask.from(request.getTask());
        Todo todo = Todo.of(todoTask, false, request.getPriority());
        todoRepository.save(todo);
        return new ViewSingleResponse(todo);
    }

    @Override
    public List<ViewSingleResponse> getAllTodos() {
        return todoRepository.findAll().stream()
                .filter(todo -> !todo.isDeleted())
                .sorted(Comparator.comparing(Todo::getPriority).reversed())
                .map(ViewSingleResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public ViewSingleResponse getSingleTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 ID의 할 일이 존재하지 않습니다."));
        if (todo.isDeleted()) {
            throw new NotFoundException("해당 ID의 할 일이 삭제되었습니다.");
        }
        return new ViewSingleResponse(todo);
    }

    @Override
    public ViewSingleResponse updateTodo(Long id, String taskStr) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 ID의 할 일이 존재하지 않습니다."));
        if (todo.isDeleted()) {
            throw new NotFoundException("해당 ID의 할 일이 삭제되었습니다.");
        }
        TodoTask task = TodoTask.from(taskStr);
        todo.setTask(task);
        todoRepository.save(todo);
        return new ViewSingleResponse(todo);
    }

    @Override
    public ViewSingleResponse deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 ID의 할 일이 존재하지 않습니다."));
        if (todo.isDeleted()) {
            throw new NotFoundException("해당 ID의 할 일은 이미 삭제되었습니다.");
        }
        todo.delete();
        return new ViewSingleResponse(todo);
    }
}
