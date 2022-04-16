package kr.codesquad.todolist.controller;

import kr.codesquad.todolist.auth.TokenProvider;
import kr.codesquad.todolist.domain.User;
import kr.codesquad.todolist.dto.LoginResponse;
import kr.codesquad.todolist.dto.UserRequest;
import kr.codesquad.todolist.dto.UserResponse;
import kr.codesquad.todolist.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    public UserController(UserService userService, TokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest createRequest) {
        User user = userService.create(createRequest.toEntity());

        return ResponseEntity.ok().body(UserResponse.from(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest loginRequest) {
        String userId = userService.login(loginRequest.toEntity());
        String token = tokenProvider.createToken(userId);
        LoginResponse loginResponse = new LoginResponse(userId, token);

        return ResponseEntity.ok().body(loginResponse);
    }
}
