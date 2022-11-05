package com.springsecurity.bootsecurity.repository;

import com.springsecurity.bootsecurity.model.Role;
import com.springsecurity.bootsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {

}
