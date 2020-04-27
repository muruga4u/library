package com.library.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dtos.BookVO;
import com.library.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

	@Autowired
	private BookService bookService;

	@PostMapping("/create")
	public ResponseEntity<Long> createBook(@RequestBody BookVO book) {
		Long id = null;
		try {
			id = bookService.createBook(book);
		} catch (Exception ex) {
			logger.error("An Error occurred while persisting the book {}", ex);
			return new ResponseEntity<>(id,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(id,HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<HttpStatus> updateBook(@RequestBody BookVO book) {
		try {
			bookService.updateBookDetails(book);
		} catch (Exception ex) {
			logger.error("An Error occurred while updating the book {}", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteBook(@RequestParam long bookId) {
		try {
			bookService.deleteBook(bookId);
		} catch (Exception ex) {
			logger.error("An Error occurred while deleting the book {}", ex);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/fetchById")
	public BookVO fetchById(@RequestParam long bookId) {
		
		BookVO book = null;
		try {
			book = bookService.fetchBookDetails(bookId);
		} catch (Exception ex) {
			logger.error("An Error occurred while fetching the book {}", ex);
		}
		return book;
	}

	@GetMapping("/fetchAll")
	public List<BookVO> fetchAllBooks() {

		List<BookVO> books = new ArrayList<>();
		try {
			books = bookService.fetchAllBookDetails();
		} catch (Exception ex) {
			logger.error("An Error occurred while fetchAllBooks {}", ex);
			
		}
		return books;
	}

}
