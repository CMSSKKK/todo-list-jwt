package kr.codesquad.todolist.dto;

import kr.codesquad.todolist.domain.User;

public class UserResponse {

    private final Long id;
    private final String userId;

    public UserResponse(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getUserId());
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
