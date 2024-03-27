package com.notescollab.notescollab.repository;

import com.notescollab.notescollab.entity.User;

import java.util.Optional;


public interface UserRepository {

    User getUserById(Long userid);
    String saveUser(User user);
    String loginUser(User user);

    Optional<User> findByUsername(String username);
}
