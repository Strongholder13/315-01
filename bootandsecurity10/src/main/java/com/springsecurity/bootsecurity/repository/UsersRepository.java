package com.springsecurity.bootsecurity.repository;


import com.springsecurity.bootsecurity.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer>{
    @Query("select user from User user join fetch user.roles where user.username = :username")
    Optional<User> findByUsername (String username);
}
