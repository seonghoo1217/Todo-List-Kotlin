package study.todolist.domain.repository;

import study.todolist.domain.ToDo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@org.springframework.stereotype.Repository
public class ToDoRepository extends Repository {
    private final ConcurrentHashMap<Long, ToDo> todoDB = new ConcurrentHashMap<>();

    public Long save(Long id, ToDo todo) {
        super.save(id, todo, todoDB);
        return id;
    }

    public Optional<ToDo> findById(Long id) {
        return Optional.ofNullable((ToDo) super.findById(id, todoDB).orElse(null));
    }

    public void deleteById(Long id) {
        super.deleteById(id, todoDB);
    }

    public List<ToDo> findAll() {
        return super.findAll(todoDB).stream()
                .filter(Objects::nonNull)
                .map(o -> (ToDo) o)
                .toList();
    }

    public void deleteAll() {
        super.deleteAll(todoDB);
    }
}
