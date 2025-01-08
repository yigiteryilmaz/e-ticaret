package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.RegisterResponseDto;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exception.ApiException;
import com.workintech.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService,UserServiceInterface {
    private UserRepository userRepository;
@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()->{
            System.out.println("User credentials are not valid");
            throw new UsernameNotFoundException("User credenrials are not valid");
        });
    }

    @Override
    public RegisterResponseDto findById(Long id) {
        Optional<User> userOptional=userRepository.findById(id);
        if (userOptional.isPresent()){
            User user=userOptional.get();
            return new RegisterResponseDto(user.getId(), user.getEmail(), user.getFirstName(),
                    user.getLastName(), user.getRole(), user.getRoles());
        }throw new ApiException("Bu id'de user bulunamadı!", HttpStatus.NOT_FOUND);
    }

    @Override
    public List<User> findAll() {
        List<User> users=userRepository.findAll();
        return users;
    }

    @Override
    public User findByUser(Long id) {
        Optional<User> userOptional=userRepository.findById(id);
        if (userOptional.isPresent()){
           return userOptional.get();

        }throw new ApiException("Bu id'de user bulunamadı!", HttpStatus.NOT_FOUND);
    }


    @Override
    public RegisterResponseDto delete(Long id) {
        User user =findByUser(id);
        userRepository.delete(user);
        return new RegisterResponseDto(user.getId(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getRole(), user.getRoles());
    }


}
