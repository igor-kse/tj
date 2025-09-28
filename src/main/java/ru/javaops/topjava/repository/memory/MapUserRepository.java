package ru.javaops.topjava.repository.memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MapUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(MapUserRepository.class);

    private static final Map<Long, User> userStorage = new ConcurrentHashMap<>();
    private static final AtomicLong lastId = new AtomicLong(0);

    @Override
    public User save(User user) {
        log.info("saving {}", user);
        if (user.isNew()) {
            user.setId(lastId.incrementAndGet());
            userStorage.put(user.getId(), user);
            return user;
        }
        return userStorage.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public User get(long id) {
        log.info("get {}", id);
        return userStorage.get(id);
    }

    @Override
    public boolean delete(long id) {
        log.info("delete {}", id);
        return userStorage.remove(id) != null;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return userStorage.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        log.info("getAll");
        return userStorage.values().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .toList();
    }
}
