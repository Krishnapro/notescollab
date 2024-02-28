package com.notescollab.notescollab.repository;

import com.notescollab.notescollab.entity.User;


public interface UserRepository {

    User getUserById(Long userid);
    User saveUser(User user);

}
