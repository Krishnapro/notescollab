package com.notescollab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notescollab.entity.MyUser;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
}
