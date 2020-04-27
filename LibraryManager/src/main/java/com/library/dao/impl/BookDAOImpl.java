package com.library.dao.impl;

import static com.library.dao.impl.EntityToVOConverter.BOOK_VO_CONVERTER;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.dao.Dao;
import com.library.dtos.BookVO;
import com.library.entity.Book;

@Component
public class BookDAOImpl implements Dao<BookVO> {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public long save(BookVO book) {
		Book entity = new Book(book);
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		return entity.getId();
	}

	@Override
	public void update(BookVO book) {
		Book entity = new Book(book);
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	@Override
	public boolean delete(Long bookId) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Book book = em.find(Book.class, bookId);
			em.getTransaction().begin();
			em.remove(book);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

		return true;
	}

	@Override
	public BookVO getDetails(long id) {
		Book book = null;
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			book = em.find(Book.class, id);
		} finally {
			em.close();
		}

		return BOOK_VO_CONVERTER.apply(book);

	}

	@Override
	public List<BookVO> fetchAll() {
		EntityManager em = entityManagerFactory.createEntityManager();
		List<Book> books = em.createQuery("select b from Book b").getResultList();
		em.close();
		return books.stream().filter(Objects::nonNull).map(book -> BOOK_VO_CONVERTER.apply(book))
				.collect(Collectors.toList());
	}

}
