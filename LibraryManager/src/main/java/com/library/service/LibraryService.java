package com.library.service;

import java.util.List;

import com.library.dtos.BookVO;
import com.library.dtos.LibraryVO;

public interface LibraryService {
	
	Long createLibrary(LibraryVO library);

	LibraryVO fetchLibrary(long libraryId);
	
	List<BookVO> fetchAllBooks(long libraryId);

	boolean deleteLibrary(long libraryId);

}
