package com.notescollab.notescollab.repository;

import com.notescollab.notescollab.entity.MyUser;

import java.util.Optional;


public interface UserRepository {

    MyUser getUserById(Long userid) throws Exception;
    String saveUser(MyUser user);
    String loginUser(MyUser user);

    Optional<MyUser> findByUsername(String username);
    Optional<MyUser> findByEmail(String emailid);
    Optional<MyUser> findByUsernameAndEmail(String username, String emailid);
}
