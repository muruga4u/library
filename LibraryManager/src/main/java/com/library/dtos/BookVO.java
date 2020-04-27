package com.library.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = BookVO.BookVOBuilder.class)
public class BookVO {

	private final Long id;

	private final String bookName;

	private final String author;

	private final String publisher;

	private final double price;

	private final String isbn;

	private BookVO(BookVOBuilder builder) {
		this.id = builder.id;
		this.bookName = builder.bookName;
		this.author =builder. author;
		this.publisher = builder.publisher;
		this.price = builder.price;
		this.isbn = builder.isbn;
	}

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	@JsonProperty("bookName")
	public String getBookName() {
		return bookName;
	}

	@JsonProperty("author")
	public String getAuthor() {
		return author;
	}

	@JsonProperty("publisher")
	public String getPublisher() {
		return publisher;
	}

	@JsonProperty("price")
	public double getPrice() {
		return price;
	}

	@JsonProperty("isbn")
	public String getIsbn() {
		return isbn;
	}

	@JsonPOJOBuilder(buildMethodName = "build")
	public static class BookVOBuilder {

		private Long id;

		private String bookName;

		private String author;

		private String publisher;

		private double price;

		private String isbn;
		
		public BookVOBuilder(BookVO builder) {
			this.id = builder.id;
			this.bookName = builder.bookName;
			this.author =builder. author;
			this.publisher = builder.publisher;
			this.price = builder.price;
			this.isbn = builder.isbn;
		}
		
		public BookVOBuilder() {
			
		}

		public BookVOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public BookVOBuilder withBookName(String bookName) {
			this.bookName = bookName;
			return this;
		}

		public BookVOBuilder withAuthor(String author) {
			this.author = author;
			return this;
		}

		public BookVOBuilder withPublisher(String publisher) {
			this.publisher = publisher;
			return this;
		}

		public BookVOBuilder withPrice(Double price) {
			this.price = price;
			return this;
		}

		public BookVOBuilder withIsbn(String isbn) {
			this.isbn = isbn;
			return this;
		}

		public BookVO build() {
			return new BookVO(this);
		}
		
		

	}

	@Override
	public String toString() {
		return "BookVO [id=" + id + ", bookName=" + bookName + ", author=" + author + ", publisher=" + publisher
				+ ", price=" + price + ", isbn=" + isbn + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		return true;
	}
	
	
	
	
}