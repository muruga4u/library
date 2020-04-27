package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.dao.LibraryDAO;
import com.library.dtos.BookVO;
import com.library.dtos.LibraryVO;
import com.library.service.LibraryService;

@Component
public class LibraryServiceImpl implements LibraryService{
	
	@Autowired
	private LibraryDAO libraryDAO;

	@Override
	public Long createLibrary(LibraryVO library) {
		return getLibraryDAO().save(library);
	}

	@Override
	public LibraryVO fetchLibrary(long libraryId) {
		return getLibraryDAO().getDetails(libraryId);
	}

	@Override
	public List<BookVO> fetchAllBooks(long libraryId) {
		return getLibraryDAO().fetchAllBooks(libraryId);
	}
	
	@Override
	public boolean deleteLibrary(long libraryId) {
		return getLibraryDAO().delete(libraryId);
	}
	
	public LibraryDAO getLibraryDAO() {
		return libraryDAO;
	}


}
