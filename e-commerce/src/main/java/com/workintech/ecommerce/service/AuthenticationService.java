package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Role;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exception.ApiException;
import com.workintech.ecommerce.repository.RoleRepository;
import com.workintech.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;
@Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String email,String firstName,String lastName,
                         String password,String phone,String role){


       if (userRepository.findByEmail(email).isPresent()){
           throw new ApiException("This email is registered in the system", HttpStatus.BAD_REQUEST);
       }
        if (userRepository.findByPhone(phone).isPresent()){
            throw new ApiException("This phone number is registered in the system", HttpStatus.NOT_FOUND);
        }


    String encodePassword=passwordEncoder.encode(password);
        String upperRole=role.toUpperCase();
        Role userRole=roleRepository.findByRole(upperRole).get();



        Set<Role> roles=new HashSet<>();
        roles.add(userRole);

        User user=new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(encodePassword);
        user.setPhone(phone);
        user.setRole(upperRole);
        user.addRole(userRole);

       return userRepository.save(user);
    }

}
