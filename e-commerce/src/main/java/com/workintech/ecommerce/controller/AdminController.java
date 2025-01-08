package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.RegisterResponseDto;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
@Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allusers")
    public List<User> adminHi(){
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public RegisterResponseDto delete(@PathVariable Long id){
       return userService.delete(id);
    }


}
