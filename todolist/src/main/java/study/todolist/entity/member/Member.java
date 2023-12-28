package study.todolist.entity.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.entity.todo.TodoList;

import java.util.List;

@Entity @Getter
@NoArgsConstructor @AllArgsConstructor
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "member")
    private List<TodoList> todoList;
}
