package com.library.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.library.dtos.BookVO;
import com.library.dtos.BookVO.BookVOBuilder;
import com.library.dtos.LibraryVO;
import com.library.dtos.LibraryVO.LibraryVOBuilder;
import com.library.entity.Library;

@RunWith(MockitoJUnitRunner.class)
public class LibraryDAOImplTest {
	
	private static final String LIBRARY_NAME = "ANNA LIBRARY";

	private static final String BOOK_NAME = "HEAD FIRST JAVA";

	private static final String AUTHOR = "Kathy Sierra";

	private static final String PUBLISHER = "OReilly";

	private static final String ISBN = "978-3-16-148410-0";

	private static final double PRICE = 250;

	private static final Long ID = 001l;
	
	@Mock
	private EntityManagerFactory entityManagerFactory;
	
	@Mock private EntityManager entityManager;
	
	@Mock private EntityTransaction entityTransaction;
	
	@Mock private Query query;
	
	@InjectMocks private LibraryDAOImpl libraryDAOImpl;
	
	
	@Test
	public void testFetchAll() {
		when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
		when(entityManager.find(Library.class, 1l)).thenReturn(getLibraryEntitiy());
		
		List<BookVO> result = libraryDAOImpl.fetchAllBooks(1l);
		
		assertNotNull(result);
		assertTrue(result.size()==1);
		assertEquals(getBookVO(), result.get(0));
		
		verify(entityManagerFactory).createEntityManager();
		verify(entityManager).find(Library.class, 1l);
	}
	
	
	
	private LibraryVO getLibraryVO() {
		LibraryVOBuilder libraryVOBuilder = new LibraryVO.LibraryVOBuilder();
		return libraryVOBuilder.withId(ID).withName(LIBRARY_NAME).withBooks(Arrays.asList(getBookVO())).build();
	}
	
	private Library getLibraryEntitiy(){
		return  new Library(getLibraryVO());
	}
	
	private BookVO getBookVO() {

		BookVOBuilder builder = new BookVO.BookVOBuilder();

		BookVO bookVo = builder.withBookName(BOOK_NAME).withPublisher(PUBLISHER).withId(ID).
				withAuthor(AUTHOR).withPrice(PRICE).withIsbn(ISBN)
				.build();

		return bookVo;
	}
	


}
