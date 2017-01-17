package com.mike.service.impl;

import com.mike.domain.Book;
import com.mike.service.BookService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;

/*
An example implementation of the BookService interface.

This particular implementation is an example of a basic javax persistence implementation of a database CRUD service.
This connects to the database and actually performs database operations to achieve its methods.

Take note of the Profile annotation, which restricts this bean from being instantiated with any other profile.
 */

@Service
@Profile("DatabaseServices")
public class BookServiceJpaImpl implements BookService {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getBookList() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query findAllBooksQuery = entityManager.createQuery("SELECT b FROM Book b");

        return findAllBooksQuery.getResultList();
    }

    @Override
    public Book createOrUpdateBook(Book book) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Book mergedBook = entityManager.merge(book);
        entityManager.getTransaction().commit();

        return mergedBook;
    }

    @Override
    public Book getBookById(Integer id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return entityManager.find(Book.class, id);
    }

    @Override
    public void deleteBook(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Book.class, id));
        entityManager.getTransaction().commit();
    }
}
