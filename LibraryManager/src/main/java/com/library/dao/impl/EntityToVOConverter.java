package com.library.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.library.dtos.BookVO;
import com.library.dtos.BookVO.BookVOBuilder;
import com.library.dtos.LibraryVO;
import com.library.dtos.LibraryVO.LibraryVOBuilder;
import com.library.entity.Book;
import com.library.entity.Library;

public class EntityToVOConverter {
	
	public static final Function<Book, BookVO> BOOK_VO_CONVERTER = (entity) -> Optional.ofNullable(entity)
			.map(book -> {
				BookVOBuilder builder = new BookVO.BookVOBuilder();
				return builder.withId(book.getId()).withAuthor(book.getAuthor()).withBookName(book.getBookName())
						.withIsbn(book.getIsbn()).withPrice(book.getPrice()).withPublisher(book.getPublisher()).build();

			}).orElse(null);
	
	public static final Function<Library, LibraryVO> LIBRARY_VO_CONVERTER = (entity) -> Optional.ofNullable(entity)
			.map(library -> {
				List<BookVO> books = library.getBooks().stream().map(book -> BOOK_VO_CONVERTER.apply(book)).collect(Collectors.toList());
				LibraryVOBuilder libraryVOBuilder = new LibraryVO.LibraryVOBuilder();
				return libraryVOBuilder.withId(library.getId()).withName(library.getName()).withBooks(books).build();

			}).orElse(null);

}
