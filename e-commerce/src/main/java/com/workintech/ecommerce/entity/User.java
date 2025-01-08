package com.workintech.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user",schema = "ecommerce")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    @NotNull
    @NotBlank(message = "email must not be empty")
    @Email(message = "email is not valid!", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotBlank(message = "firstName must not be empty")
    @Size(min = 3,message = "firstName must not be less from 3 caracters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "lastName must not be empty")
    @Size(min = 3,message = "firstName must not be less from 3 caracters")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @NotBlank(message = "password must not be empty")
    @Size(min = 3,message = "password must not be less 3 caracters")
    @Column(name = "password")
    private  String password;

    @Column(name = "phone")
    @NotNull
    @NotBlank(message = "phone must not be empty")
    private String phone;

    @Column(name = "role")
    @NotNull
    @NotBlank(message = "phone must not be empty")
    private  String role;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "user_role",schema = "ecommerce",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public void addRole(Role role){
        if(roles==null){
            roles=new HashSet<>();
        }
        roles.add(role);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
