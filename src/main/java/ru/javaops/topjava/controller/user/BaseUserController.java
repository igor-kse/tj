package ru.javaops.topjava.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.service.UserService;
import ru.javaops.topjava.util.ValidationUtil;

import java.util.List;

public abstract class BaseUserController {

    private final Logger log = LoggerFactory.getLogger(BaseUserController.class);

    @Autowired
    public UserService userService;

    public BaseUserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAll() {
        log.info("getting all users");
        return userService.getAll();
    }

    public User get(int id) {
        log.info("getting user with id: {}", id);
        return userService.get(id);
    }

    public User create(User user) {
        log.info("creating a new user: {}", user);
        ValidationUtil.checkIsNew(user);
        return userService.create(user);
    }

    public void delete(int id) {
        log.info("deleting a user with id: {}", id);
        userService.delete(id);
    }

    public void update(User user, int id) {
        log.info("update a user {} with id={}", user, id);
        ValidationUtil.assureIdConsistent(user, id);
        userService.update(user);
    }

    public User getByMail(String email) {
        log.info("getting a user by email: {}", email);
        return userService.getByEmail(email);
    }
}
