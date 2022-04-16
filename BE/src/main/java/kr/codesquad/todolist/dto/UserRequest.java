package kr.codesquad.todolist.dto;

import kr.codesquad.todolist.domain.User;

public class UserRequest {

    private String userId;
    private String password;

    public UserRequest() {
    }

    public User toEntity() {
        return new User(null, this.userId, this.password);
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
