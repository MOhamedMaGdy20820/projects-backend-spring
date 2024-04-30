package com.global.book.controller;

//import com.global.book.dto.BookDto;
import com.global.book.dto.BookDto;
import com.global.book.entity.Book;
//import com.global.book.mapper.BookMapper;
import com.global.book.service.BookService;
//import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;

@RestController
@RequestMapping("/book")
// @RequiredArgsConstructor
public class BookController {

	@Autowired
	private  BookService bookService;
	// private final BookMapper bookMapper;

//	@GetMapping("/{id}")
//	public ResponseEntity<?> findById(@PathVariable Long id) {
//
//		Book book = bookService.findById(id);
//
//		// BookDto dto = bookMapper.map(book);
//
//		return ResponseEntity.ok(book);
//	}


	@PostMapping("")
	public ResponseEntity<?> insertBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.insert(book));
	}
	@GetMapping()
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(bookService.findAll());
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {

		Book book = bookService.findById(id);
		BookDto dto = new BookDto();
		dto.setId(book.getId());
		dto.setName(book.getName());
		dto.setPrice(book.getPrice());
		dto.setDiscounted(book.getDiscounted());
		dto.setAuther(book.getAuther());
		dto.setBookCount(book.getBookCount());


		return ResponseEntity.ok(dto);
	}

//	@PostMapping("")
//	public ResponseEntity<?> insert(@RequestBody @Valid BookDto dto) {
//
//		Book book = bookMapper.unMap(dto);
//
//		return ResponseEntity.ok(bookService.insert(book));
//	}

	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody
//										@Valid
										Book book) {

		return ResponseEntity.ok(bookService.update(book));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		bookService.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@DeleteMapping("/auther/{id}")
	public ResponseEntity<?> deleteByAutherId(@PathVariable Long id) {

		return ResponseEntity.ok(bookService.deleteByAutherId(id));
	}



}
