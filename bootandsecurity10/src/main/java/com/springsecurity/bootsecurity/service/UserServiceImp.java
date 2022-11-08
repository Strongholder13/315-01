package com.springsecurity.bootsecurity.service;



import com.springsecurity.bootsecurity.model.Role;
import com.springsecurity.bootsecurity.model.User;
import com.springsecurity.bootsecurity.repository.RolesRepository;
import com.springsecurity.bootsecurity.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImp implements UserService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImp(UsersRepository usersRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    @Override
    public void add(User user, List<String> roles) {
        List<Role> roleList = new ArrayList<>();
        for (Role role : rolesRepository.findAll()) {
            if (roles.contains(role.getRole())) {
                roleList.add(role);
            }
        }
        user.setRoles(roleList);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }
    @Transactional
    @Override
    public void update(User user, List<String> roles) {
        List<Role> roleList = new ArrayList<>();
        User toChange = usersRepository.findById(user.getId()).get();
        if (roles == null) {
            roleList = toChange.getRoles();
        } else {
            for (Role role : rolesRepository.findAll()) {
                if (roles.contains(role.getRole())) {
                    roleList.add(role);
                }
            }
        }
        user.setRoles(roleList);
        if (user.getPassword() == "") {
            user.setPassword(toChange.getPassword());
        } else {
           user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        usersRepository.save(user);

    }
    @Override
    public List<User> listUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<String> listRoles() {
        List<Role> roleList = rolesRepository.findAll();
        List<String> roleNames = new ArrayList<>();
        for (Role role : roleList){
           roleNames.add(role.getRole());
        }
        return roleNames;
    }
    @Transactional
    @Override
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = usersRepository.findByUsername(username);
        return user.orElse(null);
    }
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return user.orElse(null);
    }

}
