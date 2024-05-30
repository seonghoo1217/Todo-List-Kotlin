package study.todolist.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface RepositoryInterface {
    Object save(Long id, Object object, ConcurrentHashMap<Long, Object> db);

    Optional<Object> findById(Long id, ConcurrentHashMap<Long, Object> db);

    void deleteById(Long id, ConcurrentHashMap<Long, Object> db);

    List<Object> findAll(ConcurrentHashMap<Long, Object> db);

    void deleteAll(ConcurrentHashMap<Long, Object> db);
}
