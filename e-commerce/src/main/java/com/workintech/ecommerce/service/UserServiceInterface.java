package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.RegisterResponseDto;

import com.workintech.ecommerce.entity.User;

import java.util.List;

public interface UserServiceInterface {

    public RegisterResponseDto findById(Long id);

    public List<User> findAll();

    public User findByUser(Long id);
    public RegisterResponseDto delete(Long id);
}
