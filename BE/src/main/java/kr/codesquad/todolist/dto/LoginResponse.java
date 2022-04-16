package kr.codesquad.todolist.dto;

public class LoginResponse {

    private final String userId;
    private final String token;

    public LoginResponse(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
