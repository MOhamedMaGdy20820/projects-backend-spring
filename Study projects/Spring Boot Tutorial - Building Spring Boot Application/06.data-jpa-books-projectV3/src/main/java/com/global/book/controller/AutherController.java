package com.global.book.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.Auther;
import com.global.book.repository.AutherRepo;
import com.global.book.service.AutherService;


@Validated  // use to tell spring to validate parameters that are passed into method of the annotated class
@RestController
@RequestMapping("/auther")
public class AutherController {
	
	@Autowired
	 private AutherService autherService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable @Min(value = 3) @Max(value = 40) long id) {
		
		return ResponseEntity.ok(autherService.findById(id));
	}
	
	@GetMapping("")
	public ResponseEntity<?>  findAll() {
		
		return ResponseEntity.ok(autherService.findAll());
	}
	
	@PostMapping("")
	public ResponseEntity<?>  insert (@RequestBody @Valid Auther auther) {
		
		 return ResponseEntity.ok(autherService.insert(auther));
	}
	
	@PutMapping("")
	public ResponseEntity<?>  update (@RequestBody @Valid Auther auther) {
	
		return ResponseEntity.ok(autherService.update(auther));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		
		autherService.deleteById(id);
		
	}
	

}
