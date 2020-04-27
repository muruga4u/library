package com.library.dtos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.dtos.BookVO.BookVOBuilder;

public class BookVOSerialisationTest {

	private static final String BOOK_NAME = "HEAD FIRST JAVA";

	private static final String AUTHOR = "Kathy Sierra";

	private static final String PUBLISHER = "OReilly";

	private static final String ISBN = "978-3-16-148410-0";

	private static final double PRICE = 250;

	private static final Long ID = 001l;

	private static final String EXPECTED_JSON = "{\"id\":1,\"bookName\":\"HEAD FIRST JAVA\",\"author\":\"Kathy Sierra\",\"publisher\":\"OReilly\",\"price\":250.0,\"isbn\":\"978-3-16-148410-0\"}";

	@Test
	public void testBookVoSerialisation() throws JsonProcessingException {
		BookVO book = getBookVO();
		ObjectMapper obj = new ObjectMapper();
		String result = obj.writeValueAsString(book);
		assertNotNull(result);
		assertEquals(EXPECTED_JSON, result);
	}
	
	@Test
	public void testBookVoDeSerialisation() throws JsonProcessingException {
		
		ObjectMapper obj = new ObjectMapper();
		BookVO result = obj.readValue(EXPECTED_JSON, BookVO.class);
		assertNotNull(result);
		assertEquals(AUTHOR, result.getAuthor());
		assertEquals(BOOK_NAME, result.getBookName());
		assertEquals(PUBLISHER, result.getPublisher());
		assertEquals(ID, result.getId());
		assertEquals(ISBN, result.getIsbn());
	}

	private BookVO getBookVO() {

		BookVOBuilder builder = new BookVO.BookVOBuilder();

		BookVO bookVo = builder.withBookName(BOOK_NAME).withPublisher(PUBLISHER).withId(ID).
				withAuthor(AUTHOR).withPrice(PRICE).withIsbn(ISBN)
				.build();

		return bookVo;
	}

}
