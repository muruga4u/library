package com.library.dao;

import java.util.List;

import com.library.dtos.BookVO;
import com.library.dtos.LibraryVO;

public interface LibraryDAO extends Dao<LibraryVO>{
	
	List<BookVO> fetchAllBooks(Long id);

}
