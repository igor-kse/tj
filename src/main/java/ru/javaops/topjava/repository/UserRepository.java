package ru.javaops.topjava.repository;

import ru.javaops.topjava.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(long id);

    User get(long id);

    User getByEmail(String email);

    List<User> findAll();
}
