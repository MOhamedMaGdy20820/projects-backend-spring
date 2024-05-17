package com.global.book.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.global.book.base.BaseEntity;
import com.global.book.validator.IpAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@SQLDelete(sql = "update authers set is_deleted = true where id = ?")
//@Where(clause = "is_deleted = false")
@Entity 
@Table(name = "authers")
@EntityListeners({AuditingEntityListener.class}) // jpa auditing methods
public class Auther extends BaseEntity <Long>{


	@NotBlank
	private String name;

	//@Pattern(regexp = "^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$")
	@IpAddress()
	private String ipAddress;

	@Email(message = "{validation.constraints.email.message}")
	private String email;

	//@Transient
	@Formula("(select count(*) from books book where book.auther_id = id)")
	private long bookCount;

	private String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	//@NotEmpty
	@JsonManagedReference // or @JsonIgnore
	@OneToMany(mappedBy = "auther" , cascade = CascadeType.ALL)
	private List<Book> books = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBookCount() {
		return bookCount;
	}

	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
















