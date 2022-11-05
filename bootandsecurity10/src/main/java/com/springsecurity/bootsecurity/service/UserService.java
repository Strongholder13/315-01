package com.springsecurity.bootsecurity.service;


import com.springsecurity.bootsecurity.model.Role;
import com.springsecurity.bootsecurity.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user, String role);
    void add(User user, List<Role> roles);
    void update(User user, String rolesName);
  //  void update(User user);

    List<User> listUsers();
    List<String> listRoles();
    void delete(int id);
    @Transactional
    User findByUsername(String username);

    User loadUserByUsername(String username);
}