package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.dao.Dao;
import com.library.dtos.BookVO;
import com.library.service.BookService;

@Component
public class BookServiceImpl implements BookService {

	private Dao<BookVO> bookDAO;

	@Autowired
	public BookServiceImpl(Dao<BookVO> bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	public Long createBook(BookVO book) {
		return getBookDAO().save(book);
	}

	@Override
	public BookVO fetchBookDetails(long id) {
		return getBookDAO().getDetails(id);
	}

	@Override
	public void updateBookDetails(BookVO book) {
		getBookDAO().update(book);

	}

	@Override
	public boolean deleteBook(long bookId) {
		return getBookDAO().delete(bookId);
	}

	@Override
	public List<BookVO> fetchAllBookDetails() {
		return getBookDAO().fetchAll();
	}

	public Dao<BookVO> getBookDAO() {
		return bookDAO;
	}
}
