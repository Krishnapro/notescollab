package com.notescollab.notescollab.repository;

import com.notescollab.notescollab.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
}
