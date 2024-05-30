package study.todolist.domain;

public class Member {
    private Long id;

    private String email;

    private String password;


    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Member(Long id) {
        this.id = id;
    }

    public Member() {

    }
}
