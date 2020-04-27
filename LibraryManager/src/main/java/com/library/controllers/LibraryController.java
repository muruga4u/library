package com.library.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dtos.BookVO;
import com.library.dtos.LibraryVO;
import com.library.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {

	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

	@Autowired
	private LibraryService libraryService;

	@PostMapping("/create")
	public ResponseEntity<Long> createLibrary(@RequestBody LibraryVO library) {
		Long id = null;
		try {
			id = libraryService.createLibrary(library);
		} catch (Exception ex) {
			logger.error("An Error occurred while persisting the library {}", ex);
			return new ResponseEntity<>(id,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(id,HttpStatus.OK);

	}

	@GetMapping("/fetchAllBook")
	public ResponseEntity<List<BookVO>> fetchAllBooks(@RequestParam long id) {
		List<BookVO> books = null;
		try {
			books = libraryService.fetchAllBooks(id);
		} catch (Exception ex) {
			logger.error("An Error occurred while fetching the books {}", ex);
			return new ResponseEntity<>(books, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(books, HttpStatus.OK);

	}

	@GetMapping("/delete")
	public ResponseEntity<HttpStatus> delete(@RequestParam long id) {
		try {
			libraryService.deleteLibrary(id);
		} catch (Exception ex) {
			logger.error("An Error occurred while deleting the books {}", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
