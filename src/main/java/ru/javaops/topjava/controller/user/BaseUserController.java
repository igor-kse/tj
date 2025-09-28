package ru.javaops.topjava.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.service.UserService;

import java.util.List;

import static ru.javaops.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javaops.topjava.util.ValidationUtil.checkIsNew;

public abstract class BaseUserController {

    private final Logger log = LoggerFactory.getLogger(BaseUserController.class);

    public final UserService userService;

    protected BaseUserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAll() {
        log.info("getting all users");
        return userService.getAll();
    }

    public User get(long id) {
        log.info("getting user with id: {}", id);
        return userService.get(id);
    }

    public User create(User user) {
        log.info("creating a new user: {}", user);
        checkIsNew(user);
        return userService.create(user);
    }

    public void delete(long id) {
        log.info("deleting a user with id: {}", id);
        userService.delete(id);
    }

    public void update(User user, long id) {
        log.info("update a user {} with id={}", user, id);
        assureIdConsistent(user, id);
        userService.update(user);
    }

    public User getByMail(String email) {
        log.info("getting a user by email: {}", email);
        return userService.getByEmail(email);
    }
}
