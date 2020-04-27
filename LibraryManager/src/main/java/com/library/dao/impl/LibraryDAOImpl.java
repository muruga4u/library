package com.library.dao.impl;

import static com.library.dao.impl.EntityToVOConverter.BOOK_VO_CONVERTER;
import static com.library.dao.impl.EntityToVOConverter.LIBRARY_VO_CONVERTER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.dao.LibraryDAO;
import com.library.dtos.BookVO;
import com.library.dtos.LibraryVO;
import com.library.entity.Library;

@Component
public class LibraryDAOImpl implements LibraryDAO {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public long save(LibraryVO library) {
		Library entity = new Library(library);
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
	public void update(LibraryVO library) {
		Library entity = new Library(library);
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
	public boolean delete(Long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Library library = em.find(Library.class, id);
			if (library != null) {
				em.getTransaction().begin();
				em.remove(library);
				em.getTransaction().commit();
			}
		} finally {
			em.close();
		}
		return true;
	}

	@Override
	public LibraryVO getDetails(long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Library library = em.find(Library.class, id);
		em.close();
		return LIBRARY_VO_CONVERTER.apply(library);
	}

	@Override
	public List<LibraryVO> fetchAll() {
		EntityManager em = entityManagerFactory.createEntityManager();
		List<Library> entity = em.createQuery("select L from Library L").getResultList();
		em.close();
		return entity.stream().filter(Objects::nonNull).map(library -> LIBRARY_VO_CONVERTER.apply(library))
				.collect(Collectors.toList());
	}

	@Override
	public List<BookVO> fetchAllBooks(Long id) {
		List<BookVO> books = new ArrayList<>();
		EntityManager em = entityManagerFactory.createEntityManager();
		Library library = em.find(Library.class, id);
		if (library != null) {
			books = Optional.ofNullable(library.getBooks()).orElse(Collections.emptyList()).stream()
					.map(book -> BOOK_VO_CONVERTER.apply(book)).collect(Collectors.toList());
		}
		return books;
	}
}
