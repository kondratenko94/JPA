package net.kondratenko.service;

import net.kondratenko.model.User;

public interface UserService {

    User getById(Long id);

    User getByEmail(String email);

    void saveUser(User user);

    boolean activateUser(String token);

    boolean emailExists(String email);

}