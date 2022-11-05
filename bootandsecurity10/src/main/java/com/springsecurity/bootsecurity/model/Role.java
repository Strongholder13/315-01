package com.springsecurity.bootsecurity.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name= "roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String role;



//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    @JoinTable(
//            name = "user_roly",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "roly_id") }
//    )
   //private Set<User> user;


    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }
//    @ManyToMany
//    private List<User> users;

    public String getRole() {
        return role;
    }

//    public List<String> getRoleNames() {
//        List<String> roleNames = new ArrayList();
//        for (Role role : user.getRoles()){
//            roleNames.add(role.getRole());
//        }
//        return roleNames;
//    }





    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role1)) return false;
        return id == id && role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return this.role.substring(5);
    }
    @Override
    public String getAuthority() {
        return this.role;
    }
}
