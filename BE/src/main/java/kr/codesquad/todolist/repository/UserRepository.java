package kr.codesquad.todolist.repository;

import kr.codesquad.todolist.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private final AtomicLong sequence = new AtomicLong(1L);
    private final Map<Long, User> users = new ConcurrentHashMap<>();


    public User save(User user) {
        long id = sequence.getAndAdd(1L);
        users.put(id, user);
        return new User(id, user);
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> findByUserId(String userId) {
        return users.values()
                .stream()
                .filter(u -> u.hasSameId(userId))
                .findFirst();
    }


}
