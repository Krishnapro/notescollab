package com.notescollab.notescollab.repository;

import com.notescollab.notescollab.entity.MyUser;

import java.util.Optional;


public interface UserRepository {

    MyUser getUserById(Long userid);
    String saveUser(MyUser user);
    String loginUser(MyUser user);

    Optional<MyUser> findByUsername(String username);
}
