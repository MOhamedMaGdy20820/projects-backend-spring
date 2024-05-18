package com.global.book.entity;

import com.global.book.base.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Setter
@Getter
public class BookFavorate extends BaseEntity<Long>{
	
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "auther_id")
	private Auther auther;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "book_id")
	private Book book;

}
