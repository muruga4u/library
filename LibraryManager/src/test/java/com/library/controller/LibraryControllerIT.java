package com.library.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.dtos.BookVO;
import com.library.dtos.BookVO.BookVOBuilder;
import com.library.dtos.LibraryVO;
import com.library.dtos.LibraryVO.LibraryVOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

public class LibraryControllerIT {
	
	private static final String LIBRARY_NAME = "ANNA LIBRARY";

	private static final String BOOK_NAME = "HEAD FIRST JAVA";

	private static final String AUTHOR = "Kathy Sierra";

	private static final String PUBLISHER = "OReilly";

	private static final String ISBN = "978-3-16-148410-0";

	private static final double PRICE = 250;

	private static final Long ID = 001l;
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	
	@Test
	public void testCreateLibrary() {
		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/library/create"), getLibraryVO(),
				Long.class);
		Long result = response.getBody();
		assertNotNull(result);
		assertTrue(result > 0);
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}
	
	
	@Test
	public void testfetchAllBooks() {
		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/library/create"), getLibraryVO(),
				Long.class);
		Long id = response.getBody();
		
		ResponseEntity<List<BookVO>> result = restTemplate.exchange(createURLWithPort("/library/fetchAllBook?id="+id), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<BookVO>>() {
				});
		List<BookVO> books = result.getBody();
		assertNotNull(books);
		assertTrue(books.size()==1);
		assertTrue(result.getStatusCode().is2xxSuccessful());
	}
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	private BookVO getBookVO() {

		BookVOBuilder builder = new BookVO.BookVOBuilder();

		BookVO bookVo = builder.withBookName(BOOK_NAME).withPublisher(PUBLISHER).withAuthor(AUTHOR)
				.withPrice(PRICE).withIsbn(ISBN).build();

		return bookVo;
	}

	private LibraryVO getLibraryVO() {
		LibraryVOBuilder libraryVOBuilder = new LibraryVO.LibraryVOBuilder();
		return libraryVOBuilder.withName(LIBRARY_NAME).withBooks(Arrays.asList(getBookVO())).build();
	}

}
