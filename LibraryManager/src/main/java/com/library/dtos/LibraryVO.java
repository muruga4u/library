package com.library.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = LibraryVO.LibraryVOBuilder.class)
public class LibraryVO {
	
private final Long id;

private final String name;

private final List<BookVO> books;


private LibraryVO(LibraryVOBuilder libraryVOBuilder) {
	super();
	this.id = libraryVOBuilder.id;
	this.name = libraryVOBuilder.name;
	this.books = libraryVOBuilder.books;
}

@JsonProperty("id")
public Long getId() {
	return id;
}


@JsonProperty("name")
public String getName() {
	return name;
}

@JsonProperty("books")
public List<BookVO> getBooks() {
	return books;
}

@JsonPOJOBuilder(buildMethodName = "build")
public static class LibraryVOBuilder {
	
	private String name;
	private Long id;
	private List<BookVO> books;
	
	
	public LibraryVOBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	

	public LibraryVOBuilder withName(String name) {
		this.name = name;
		return this;
	}
	public LibraryVOBuilder withBooks(List<BookVO> books) {
		this.books = books;
		return this;
	}
	
	
	public LibraryVO build() {
		return new LibraryVO(this);
	}
}

@Override
public String toString() {
	return "LibraryVO [id=" + id + ", name=" + name + ", books=" + books + "]";
}



}
