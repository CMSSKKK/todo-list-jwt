package kr.codesquad.todolist.service;

import kr.codesquad.todolist.domain.User;
import kr.codesquad.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public String login(User user) {
        User loginUser = userRepository.findByUserId(user.getUserId()).orElseThrow(NoSuchElementException::new);
        if (loginUser.matchPassword(user.getPassword())) {
            return loginUser.getUserId();
        };
        throw new IllegalArgumentException();
    }


}
