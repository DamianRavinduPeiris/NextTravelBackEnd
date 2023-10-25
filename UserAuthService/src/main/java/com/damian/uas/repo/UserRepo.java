package com.damian.uas.repo;


import com.damian.uas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {
    Optional<User> findByUserName(String userName);
    Optional<User>findByName(String name);
    @Query("SELECT u.name FROM User u")
    List<String>getAllNames();
    @Query("SELECT u.userId FROM User u")
    List<String>getAllIds();

}
