package com.global.book.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.global.book.base.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;


@NamedEntityGraph(name = "loadAuther" , attributeNodes = @NamedAttributeNode("auther"))
@Entity
@Table(name = "books")
public class Book extends BaseEntity<Long> {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//
	private String name;
	
	private double price;

	@Transient
	private double discounted = 1 - 0.25;

	//@Transient
	@Formula("(select count(*) from books)")
	private long bookCount;



	@ManyToOne(fetch = FetchType.LAZY)
	 @JsonBackReference // or  @JsonIgnore
	// @JsonIgnoreProperties( "hibernateLazyInitializer") // with FetchType.LAZY
	@JoinColumn(name = "auther_id")
	private Auther auther;

	public double getDiscounted() {
		return discounted;
	}

	public void setDiscounted(double discounted) {
		this.discounted = discounted;
	}

	@PostLoad // will work just one time when the object created
	private void calcDiscounted(){
		this.setDiscounted(price * discounted);
	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Auther getAuther() {
		return auther;
	}

	public void setAuther(Auther auther) {
		this.auther = auther;
	}

	public long getBookCount() {
		return bookCount;
	}

	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}
}
