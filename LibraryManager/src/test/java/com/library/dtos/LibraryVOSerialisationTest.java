package com.library.dtos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.dtos.BookVO.BookVOBuilder;
import com.library.dtos.LibraryVO.LibraryVOBuilder;

public class LibraryVOSerialisationTest {

	private static final String LIBRARY_NAME = "ANNA LIBRARY";

	private static final String BOOK_NAME = "HEAD FIRST JAVA";

	private static final String AUTHOR = "Kathy Sierra";

	private static final String PUBLISHER = "OReilly";

	private static final String ISBN = "978-3-16-148410-0";

	private static final double PRICE = 250;

	private static final Long ID = 001l;

	private static final String EXPECTED_JSON = "{\"id\":1,\"name\":\"ANNA LIBRARY\",\"books\":[{\"id\":1,\"bookName\":\"HEAD FIRST JAVA\",\"author\":\"Kathy Sierra\",\"publisher\":\"OReilly\",\"price\":250.0,\"isbn\":\"978-3-16-148410-0\"}]}";
	
	@Test
	public void testLibraryVoSerialisation() throws JsonProcessingException {
		LibraryVO library = getLibraryVO();
		ObjectMapper obj = new ObjectMapper();
		String result = obj.writeValueAsString(library);
		assertNotNull(result);
		assertEquals(EXPECTED_JSON, result);
	}

	@Test
	public void testLibraryVoDeSerialisation() throws JsonProcessingException {

		ObjectMapper obj = new ObjectMapper();
		LibraryVO result = obj.readValue(EXPECTED_JSON, LibraryVO.class);
		assertNotNull(result);
		assertEquals(LIBRARY_NAME, result.getName());
		assertEquals(ID, result.getId());
		assertEquals(AUTHOR, result.getBooks().get(0).getAuthor());
		assertEquals(BOOK_NAME, result.getBooks().get(0).getBookName());
		assertEquals(PUBLISHER, result.getBooks().get(0).getPublisher());
		assertEquals(ID, result.getBooks().get(0).getId());
		assertEquals(ISBN, result.getBooks().get(0).getIsbn());
	}

	private BookVO getBookVO() {

		BookVOBuilder builder = new BookVO.BookVOBuilder();

		BookVO bookVo = builder.withBookName(BOOK_NAME).withPublisher(PUBLISHER).withId(ID).withAuthor(AUTHOR)
				.withPrice(PRICE).withIsbn(ISBN).build();

		return bookVo;
	}

	private LibraryVO getLibraryVO() {
		LibraryVOBuilder libraryVOBuilder = new LibraryVO.LibraryVOBuilder();
		return libraryVOBuilder.withId(ID).withName(LIBRARY_NAME).withBooks(Arrays.asList(getBookVO())).build();
	}

}
