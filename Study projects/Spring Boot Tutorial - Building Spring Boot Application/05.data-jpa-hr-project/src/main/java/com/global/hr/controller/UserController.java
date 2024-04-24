package com.global.hr.controller;

import com.global.hr.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.global.hr.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/role/{roleName}")
	public ResponseEntity<?> addRoleForAllUsers(@PathVariable String roleName) {
	
		userService.addRoleForAllUsers(roleName);
		
		return ResponseEntity.ok(null);
	}

	@PostMapping ()
	public boolean addUesr(@RequestBody User user) {
		userService.insert(user);
		return true;
	}

}
