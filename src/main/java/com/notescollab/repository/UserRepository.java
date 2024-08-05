package com.notescollab.repository;

import java.util.Optional;

import com.notescollab.entity.MyUser;


public interface UserRepository {

    MyUser getUserById(Long userid) throws Exception;
    String saveUser(MyUser user);
    String loginUser(MyUser user);

    Optional<MyUser> findByUsername(String username);
    Optional<MyUser> findByEmail(String emailid);
    Optional<MyUser> findByUsernameAndEmail(String username, String emailid);
}
