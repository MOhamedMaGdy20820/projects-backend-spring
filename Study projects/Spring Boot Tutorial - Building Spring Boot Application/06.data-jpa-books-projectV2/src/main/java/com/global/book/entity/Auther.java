package com.global.book.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.global.book.base.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity 
@Table(name = "authers")
@EntityListeners({AuditingEntityListener.class}) // jpa auditing methods

 						  // datatype of id
public class Auther extends BaseEntity <Long>{
	
//	@Id // in base Entity
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//
	private String name;

	//@Transient
	@Formula("(select count(*) from books book where book.auther_id = id)")
	private long bookCount;


	//@NotEmpty
	@JsonManagedReference // or @JsonIgnore
	@OneToMany(mappedBy = "auther" , cascade = CascadeType.ALL)
	private List<Book> books = new ArrayList<>();

//	@CreatedBy  // in baseEntity
//	private String createdBy;
//
//	@CreatedDate
//	private LocalDateTime createdDate;
//
//	@LastModifiedBy
//	private String lastModifiedBy;
//
//	@LastModifiedDate
//	private LocalDateTime lastModifiedDate;

	//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

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



	public String getName() {
		return name;
	}

	public void setName(String naem) {
		this.name = naem;
	}

	public long getBookCount() {
		return bookCount;
	}

	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}



}
















