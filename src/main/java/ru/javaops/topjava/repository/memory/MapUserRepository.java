package ru.javaops.topjava.repository.memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.repository.UserRepository;

import java.util.Collections;
import java.util.List;

public class MapUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(MapUserRepository.class);

    @Override
    public boolean delete(long id) {
        log.info("delete {}", id);
        return true;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        return user;
    }

    @Override
    public User get(long id) {
        log.info("get {}", id);
        return null;
    }

    @Override
    public List<User> findAll() {
        log.info("getAll");
        return Collections.emptyList();
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return null;
    }
}
