package com.library.service;

import java.util.List;

import com.library.dtos.BookVO;

public interface BookService {

	Long createBook(BookVO book);

	BookVO fetchBookDetails(long bookId);

	void updateBookDetails(BookVO book);

	boolean deleteBook(long bookId);

	List<BookVO> fetchAllBookDetails();

}
