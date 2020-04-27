package com.library.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.library.dtos.BookVO;
import com.library.dtos.BookVO.BookVOBuilder;
import com.library.entity.Book;


@RunWith(MockitoJUnitRunner.class)
public class BookDAOImplTest {
	
	
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
	
	
	@InjectMocks
	private BookDAOImpl bookDAOImpl;
	
	@Test
	public void testSave() {
		BookVO book = getBookVO();
		when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
		when(entityManager.getTransaction()).thenReturn(entityTransaction);
		doNothing().when(entityTransaction).begin();
		doNothing().when(entityTransaction).commit();
		doNothing().when(entityManager).persist(ArgumentMatchers.any());
		
		bookDAOImpl.save(book);
		
		verify(entityManagerFactory).createEntityManager();
		verify(entityManager,times(2)).getTransaction();
		verify(entityManager).persist(ArgumentMatchers.any());
		verify(entityTransaction).begin();
		verify(entityTransaction).commit();
		
	}
	
	@Test(expected = Exception.class)
	public void testSave_onException() {
		BookVO book = getBookVO();
		doThrow(Exception.class).when(entityManager).persist(ArgumentMatchers.any());
		
		bookDAOImpl.save(book);	
	}
	
	@Test
	public void testUpdate() {
		BookVO book = getBookVO();
		when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
		when(entityManager.getTransaction()).thenReturn(entityTransaction);
		doNothing().when(entityTransaction).begin();
		doNothing().when(entityTransaction).commit();
		when(entityManager.merge(ArgumentMatchers.any())).thenReturn(null);
		
		bookDAOImpl.update(book);
		
		verify(entityManagerFactory).createEntityManager();
		verify(entityManager,times(2)).getTransaction();
		verify(entityManager).merge(ArgumentMatchers.any());
		verify(entityTransaction).begin();
		verify(entityTransaction).commit();
		
	}
	
	@Test
	public void testDelete() {
		BookVO book = getBookVO();
		Book entity = new Book(book);
		when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
		when(entityManager.getTransaction()).thenReturn(entityTransaction);
		when(entityManager.find(Book.class, 1l)).thenReturn(entity);
		doNothing().when(entityTransaction).begin();
		doNothing().when(entityTransaction).commit();
		
		bookDAOImpl.delete(1l);
		
		verify(entityManagerFactory).createEntityManager();
		verify(entityManager,times(2)).getTransaction();
		verify(entityManager).find(Book.class, 1l);
		verify(entityManager).remove(entity);
		verify(entityTransaction).begin();
		verify(entityTransaction).commit();
		
	}
	
	@Test
	public void testGetDetails() {
		BookVO book = getBookVO();
		Book entity = new Book(book);
		
		when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
		when(entityManager.find(Book.class, 1l)).thenReturn(entity);
		
		BookVO result = bookDAOImpl.getDetails(1l);
		
		assertNotNull(result);
		assertEquals(book, result);
		
		verify(entityManagerFactory).createEntityManager();
		verify(entityManager).find(Book.class, 1l);	
	}
	
	@Test
	public void testFetchAll() {

		
		when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
		when(entityManager.createQuery(ArgumentMatchers.anyString())).thenReturn(query);
		when(query.getResultList()).thenReturn(getAllBooks());
		
		List<BookVO> result = bookDAOImpl.fetchAll();
		
		assertNotNull(result);
		assertTrue(result.size()==1);
		assertEquals(getBookVO(), result.get(0));
		
		verify(entityManagerFactory).createEntityManager();
		verify(entityManager).createQuery(ArgumentMatchers.anyString());
		verify(query).getResultList();
	}
	
	private BookVO getBookVO() {

		BookVOBuilder builder = new BookVO.BookVOBuilder();

		BookVO bookVo = builder.withBookName(BOOK_NAME).withPublisher(PUBLISHER).withId(ID).
				withAuthor(AUTHOR).withPrice(PRICE).withIsbn(ISBN)
				.build();

		return bookVo;
	}
	
	private List<Book> getAllBooks(){
		List<Book> books = new ArrayList<>();
		books.add(new Book(getBookVO()));
		return books;
	}
	

}
