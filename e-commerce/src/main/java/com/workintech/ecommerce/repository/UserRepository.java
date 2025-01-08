package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email=:email ")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.phone=:phone ")
    Optional<User> findByPhone(String phone);

}