package com.springsecurity.bootsecurity.model;

import lombok.Data;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;


@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Username")
    @NonNull
    private String username;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Age")
    private int age;

    @Column(name = "Password")
    @NonNull
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private List<Role> roles;



    public User(int Id, @NonNull String username, String name, String surname, int age, @NonNull String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.password = password;

    }

    public User() {

    }

    @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id='" + id +
                ", username='" + username +
                ", name='" + name +
                ", surname='" + surname +
                ", age=" + age +
                ", password='" + password +
                ", Roles='" + roles +
                '}';
    }
}
