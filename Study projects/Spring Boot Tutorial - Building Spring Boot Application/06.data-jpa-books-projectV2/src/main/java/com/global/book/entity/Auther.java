package com.global.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;

@Entity 
@Table(name = "authers")
public class Auther {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;

	//@Transient
	@Formula("(select count(*) from books book where book.auther_id = id)")
	private long bookCount;

	public long getBookCount() {
		return bookCount;
	}

	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}

	//@NotEmpty
	@JsonManagedReference // or @JsonIgnore
	@OneToMany(mappedBy = "auther" , cascade = CascadeType.ALL)
	private List<Book> books = new ArrayList<>();

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {  // helper method
		books.add(book);
	}
	public void removeBook(Book book) {
		books.remove(book);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String naem) {
		this.name = naem;
	}



}
