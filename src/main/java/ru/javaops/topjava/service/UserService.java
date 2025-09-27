package ru.javaops.topjava.service;

import ru.javaops.topjava.model.User;
import ru.javaops.topjava.repository.UserRepository;
import ru.javaops.topjava.util.ValidationUtil;

import java.util.List;

public class UserService {

    private static final String USER_WITH_ID_NOT_FOUND = "User not found: id = %d";
    private static final String USER_WITH_EMAIL_NOT_FOUND = "User not found: %s";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User get(long id) {
        var user = userRepository.get(id);
        return ValidationUtil.checkNotFound(user, String.format(USER_WITH_ID_NOT_FOUND, id));
    }

    public void update(User user) {
        User updated = userRepository.save(user);
        ValidationUtil.checkNotFound(updated, String.format(USER_WITH_ID_NOT_FOUND, user.getId()));
    }

    public void delete(long id) {
        boolean deleted = userRepository.delete(id);
        ValidationUtil.checkNotFound(deleted, String.format(USER_WITH_ID_NOT_FOUND, id));
    }

    public User getByEmail(String email) {
        var user = userRepository.getByEmail(email);
        return ValidationUtil.checkNotFound(user, String.format(USER_WITH_EMAIL_NOT_FOUND, email));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
