package com.workintech.ecommerce.dto;

import org.springframework.validation.annotation.Validated;

@Validated
public record RegisterUserDto( String email, String firstName, String lastName, String password, String phone, String role) {
}
