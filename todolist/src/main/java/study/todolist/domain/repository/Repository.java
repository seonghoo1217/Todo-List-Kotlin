package study.todolist.domain.repository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public abstract class Repository implements RepositoryInterface {

    @Override
    public Object save(Long id, Object object, ConcurrentHashMap db) {
        if (db.containsKey(id)) {
            id++;
            save(id, object, db);
        }
        db.put(id, object);
        return db.get(id);
    }

    @Override
    public Optional<Object> findById(Long id, ConcurrentHashMap db) {
        return Optional.ofNullable(db.get(id));
    }

    ;

    @Override
    public void deleteById(Long id, ConcurrentHashMap db) {
        db.remove(id);
    }

    @Override
    public List<Object> findAll(ConcurrentHashMap db) {
        return db.values().stream().toList();
    }

    @Override
    public void deleteAll(ConcurrentHashMap db) {
        db.clear();
    }
}
