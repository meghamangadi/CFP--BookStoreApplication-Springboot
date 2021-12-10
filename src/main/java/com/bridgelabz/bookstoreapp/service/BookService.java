package com.bridgelabz.bookstoreapp.service;

import java.util.List;

import com.bridgelabz.bookstoreapp.dto.BookDto;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.exception.BookException;
import com.bridgelabz.bookstoreapp.exception.UserException;

public interface BookService {

	public Book addBook(BookDto dto, String token) throws UserException;

	List<Book> displayBooks();

	Book displaySingleBook(Long id, String token) throws BookException, UserException;

	public Book updateBook(Long bookId, BookDto dto, String token) throws BookException, UserException;

	public Book delete(Long bookId, String token) throws BookException, UserException;

}
