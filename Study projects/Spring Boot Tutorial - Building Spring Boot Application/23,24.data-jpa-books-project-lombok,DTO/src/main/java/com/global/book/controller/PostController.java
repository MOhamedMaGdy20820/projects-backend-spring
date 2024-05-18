package com.global.book.controller;

import com.global.book.dto.*;
import com.global.book.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPostById (@PathVariable Long id) {
	
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	
	@GetMapping("")
	public ResponseEntity<?> getAllPost () {
	
		return ResponseEntity.ok(postService.getAllPost());
	}
	
	
	@PostMapping("")
	public ResponseEntity<?> addPost (@RequestBody PostDto dto) {
	
		return ResponseEntity.ok(postService.addPost(dto));
	}

}
