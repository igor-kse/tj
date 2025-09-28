package ru.javaops.topjava.controller.user;

import org.springframework.stereotype.Controller;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.service.UserService;

import java.util.List;

@Controller
public class AdminRestController extends BaseUserController {

    public AdminRestController(UserService userService) {
        super(userService);
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(long id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public void update(User user, long id) {
        super.update(user, id);
    }

    @Override
    public User getByMail(String email) {
        return super.getByMail(email);
    }
}
