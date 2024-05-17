package com.global.book.controller;

import java.util.List;

import com.global.book.entity.AutherSearch;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> findById(@PathVariable  @Max(value = 40) long id) {
        return ResponseEntity.ok(autherService.findById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(autherService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody @Valid Auther auther) {
        return ResponseEntity.ok(autherService.insert(auther));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody @Valid Auther auther) {

        return ResponseEntity.ok(autherService.update(auther));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        autherService.deleteById(id);
    }

//	@GetMapping("/spec")
//	public ResponseEntity<?> findByAutherSpec(@RequestParam String autherSpec) {
//		return ResponseEntity.ok(autherService.findByAutherSpec(autherSpec));
//	}

    @PostMapping("/spec")
    public ResponseEntity<?> findByAutherSpec(@RequestBody AutherSearch autherSearch) {
        return ResponseEntity.ok(autherService.findByAutherSpec(autherSearch));
    }


    @GetMapping("/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {

        return ResponseEntity.ok(autherService.findByEmailA(email));
    }

}
