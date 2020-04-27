package com.library.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookControllerIT {

	private static final String BOOK_NAME = "HEAD FIRST JAVA";

	private static final String BOOK_NAME_UPDATED = "Rich Dad Poor Dad";

	private static final String AUTHOR = "Kathy Sierra";

	private static final String PUBLISHER = "OReilly";

	private static final String ISBN = "978-3-16-148410-0";

	private static final double PRICE = 250;

	private static final Long ID = 001l;

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testCreateBook() {

		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/book/create"), getBookVO(),
				Long.class);
		Long result = response.getBody();
		assertNotNull(result);
		assertTrue(result > 0);
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	@Test
	public void testUpdateBook() {
		BookVO book = getBookVO();

		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/book/create"), book, Long.class);
		Long id = response.getBody();

		BookVO bookVO = new BookVO.BookVOBuilder(book).withBookName(BOOK_NAME_UPDATED).build();
		restTemplate.put(createURLWithPort("/book/update"), bookVO);
	}

	@Test
	public void testFetchBYId() {
		BookVO book = getBookVO();
		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/book/create"), book, Long.class);
		Long id = response.getBody();

		BookVO bookVO = new BookVO.BookVOBuilder(book).withId(id).build();
		BookVO result = restTemplate.getForObject(createURLWithPort("/book/fetchById?bookId="+id), BookVO.class);

		assertNotNull(response);
		assertEquals(bookVO, result);
	}

	@Test
	public void testFetchAll() {
		BookVO book = getBookVO();
		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/book/create"), book, Long.class);
		Long id = response.getBody();

		BookVO bookVO = new BookVO.BookVOBuilder(book).withId(id).build();
		ResponseEntity<List<BookVO>> result = restTemplate.exchange(createURLWithPort("/book/fetchAll"), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<BookVO>>() {
				});
		List<BookVO> books = result.getBody();

		assertNotNull(books);
		assertTrue(books.contains(bookVO));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	private BookVO getBookVO() {

		BookVOBuilder builder = new BookVO.BookVOBuilder();

		BookVO bookVo = builder.withBookName(BOOK_NAME).withPublisher(PUBLISHER).withAuthor(AUTHOR).withPrice(PRICE)
				.withIsbn(ISBN).build();

		return bookVo;
	}

}
