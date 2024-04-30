package com.global.book.dto;


import com.global.book.entity.Auther;

//@Builder
public class BookDto {

	private long id;
	private String name ;
	private double price;
	private long bookCount;

	public long getBookCount() {
		return bookCount;
	}

	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}

	private double discounted ;
	private Auther auther;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Auther getAuther() {
		return auther;
	}

	public void setAuther(Auther auther) {
		this.auther = auther;
	}

	public double getDiscounted() {
		return discounted;
	}

	public void setDiscounted(double discounted) {
		this.discounted = discounted;
	}

}






























