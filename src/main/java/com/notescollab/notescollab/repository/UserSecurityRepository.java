package com.notescollab.notescollab.repository;

import com.notescollab.notescollab.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
