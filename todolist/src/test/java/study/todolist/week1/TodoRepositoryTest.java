package study.todolist.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.todolist.domain.Category;
import study.todolist.domain.Member;
import study.todolist.domain.ToDo;
import study.todolist.domain.repository.ToDoRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private ToDoRepository toDoRepository;

    @BeforeEach
    public void setUpDummy(){
        toDoRepository.deleteAll();

        for (int i=1;i<=10;i++){
            ToDo toDo = new ToDo("test title" + i, "test contents" + i, Category.SIMPLE, LocalDateTime.now(), 0, new Member());
            toDoRepository.save(1L, toDo);
        }
    }

    @Test
    @DisplayName("DB 저장시 ID 값 증가 테스트")
    public void todoRepositoryIdAutoIncrementTest(){
        //given
        ToDo toDo = new ToDo("test title" + 11, "test contents" + 11, Category.SIMPLE, LocalDateTime.now(), 0, new Member());

        //when
        ToDo saveToDo = toDoRepository.save(1L, toDo);

        //then
        assertThat(saveToDo.hashCode()).isEqualTo(toDoRepository.findById(11L).hashCode());
    }

    @Test
    @DisplayName("DB findAll 메서드 테스트")
    public void todoRepositoryFindAllTest(){
        //given && when
        List<ToDo> todoList = toDoRepository.findAll();

        assertThat(todoList.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("DB 삭제 로직 테스트")
    public void todoRepositoryDeleteTest(){
        //given
        Long targetId = 10L;

        //when
        toDoRepository.deleteById(targetId);

        //then
        assertThat(toDoRepository.findAll().size()).isEqualTo(9);
    }
}
