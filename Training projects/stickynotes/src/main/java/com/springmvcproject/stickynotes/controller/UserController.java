package com.springmvcproject.stickynotes.controller;

import com.springmvcproject.stickynotes.model.entity.UserEntity;
import com.springmvcproject.stickynotes.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserRepo userRepo;

    @GetMapping("")
    public List<UserEntity> index(){
        List<UserEntity> users = userRepo.findAll();
        System.out.println(users.toString());
        return users;
    }
}
