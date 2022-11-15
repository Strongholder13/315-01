package com.springsecurity.bootsecurity.repository;

import com.springsecurity.bootsecurity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {

}
