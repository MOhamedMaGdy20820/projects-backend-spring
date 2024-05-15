package com.global.book.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.global.book.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;


@NamedStoredProcedureQuery(name = "Book.getBookByAuther",  // stored Procedure (13-4)
		procedureName = "GET_BOOK_BY_AUTHER", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "auther_id_in", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "book_count", type = Integer.class)})


@NamedEntityGraph(name = "loadAuther" , attributeNodes = @NamedAttributeNode("auther"))
@Entity
@Table(name = "books")
public class Book extends BaseEntity<Long> {

	@NotNull(message = "Should be enter book name")
	private String name;

	@Min(value = 5)
	@Max(value = 1000)
	private double price;

	@Transient
	private double discounted = 1 - 0.25;

	//@Transient
	@Formula("(select count(*) from books)")
	private long bookCount;


	@NotNull
	@JsonBackReference // or  @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
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

	public long getBookCount() {
		return bookCount;
	}

	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}

	public Auther getAuther() {
		return auther;
	}

	public void setAuther(Auther auther) {
		this.auther = auther;
	}
}
