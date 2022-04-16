package kr.codesquad.todolist.domain;

import java.util.Objects;

public class User {

    private final Long id;
    private final String userId;
    private final String password;

    public User(Long id, String userId, String password) {
        this.id = id;
        this.userId = userId;
        this.password = password;
    }

    public User(Long id, User user) {
        this.id = id;
        this.userId = user.getUserId();
        this.password = user.getPassword();
    }

    public boolean hasSameId(String userId) {
        return this.userId.equals(userId);
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && getUserId().equals(user.getUserId()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getPassword());
    }
}
