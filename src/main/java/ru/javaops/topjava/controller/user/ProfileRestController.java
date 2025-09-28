package ru.javaops.topjava.controller.user;

import org.springframework.stereotype.Controller;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.service.UserService;
import ru.javaops.topjava.util.SecurityUtil;

@Controller
public class ProfileRestController extends BaseUserController {
    public ProfileRestController(UserService userService) {
        super(userService);
    }

    public User get() {
        return super.get(SecurityUtil.authUserId());
    }

    public void delete() {
        super.delete(SecurityUtil.authUserId());
    }

    public void update(User user) {
        super.update(user, SecurityUtil.authUserId());
    }
}
