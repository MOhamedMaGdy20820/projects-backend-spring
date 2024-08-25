package com.app.LMS.controllers;

import com.app.LMS.dto.User;
import com.app.LMS.services.LoginService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class usersController {

    private final LoginService loginService;

    @PostMapping("")
    public ResponseEntity<Boolean> login (@RequestBody @Valid User user) {
        return  ResponseEntity.status(HttpStatus.OK).body(loginService.login(user));
    }

}
