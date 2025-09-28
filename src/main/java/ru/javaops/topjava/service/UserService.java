package ru.javaops.topjava.service;

import org.springframework.stereotype.Service;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.repository.UserRepository;
import ru.javaops.topjava.util.ValidationUtil;

import java.util.List;

import static ru.javaops.topjava.util.ValidationUtil.*;
import static ru.javaops.topjava.util.ValidationUtil.checkIsNew;

@Service
public class UserService {

    private static final String EMAIL_NOT_NULL = "Email cannot be null";
    private static final String USER_NOT_NULL = "User cannot be null";
    private static final String USER_WITH_ID_NOT_FOUND = "User not found: id = %d";
    private static final String USER_WITH_EMAIL_NOT_FOUND = "User not found: %s";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        assureNotNull(user, USER_NOT_NULL);
        checkIsNew(user);
        return userRepository.save(user);
    }

    public User get(long id) {
        var user = userRepository.get(id);
        return checkNotFound(user, String.format(USER_WITH_ID_NOT_FOUND, id));
    }

    public void update(User user) {
        assureNotNull(user, USER_NOT_NULL);
        User updated = userRepository.save(user);
        checkNotFound(updated, String.format(USER_WITH_ID_NOT_FOUND, user.getId()));
    }

    public void delete(long id) {
        boolean deleted = userRepository.delete(id);
        checkNotFound(deleted, String.format(USER_WITH_ID_NOT_FOUND, id));
    }

    public User getByEmail(String email) {
        assureNotNull(email, EMAIL_NOT_NULL);
        var user = userRepository.getByEmail(email);
        return checkNotFound(user, String.format(USER_WITH_EMAIL_NOT_FOUND, email));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
