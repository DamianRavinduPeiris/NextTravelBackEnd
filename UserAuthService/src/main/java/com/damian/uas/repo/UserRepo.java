package com.damian.uas.repo;


import com.damian.uas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {
    Optional<User> findByUserName(String userName);
}
