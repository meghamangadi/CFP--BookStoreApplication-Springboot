package com.bridgelabz.bookstoreapp.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.BookDto;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Users;
import com.bridgelabz.bookstoreapp.exception.BookException;
import com.bridgelabz.bookstoreapp.exception.UserException;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.service.BookService;
import com.bridgelabz.bookstoreapp.utils.TokenUtils;

@Service
public class BookServiceImplementation implements BookService {

	@Autowired
	public BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Override
	public Book addBook(BookDto bookDto , String token) throws UserException {
		Long Id = tokenUtils.decodeToken(token);
		Users users = userRepository.findById(Id)
				.orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "USER_NOT_FOUND_EXCEPTION_MESSAGE"));
		Book book = new Book();
		BeanUtils.copyProperties(bookDto, book);
		bookRepository.save(book);
		return book;
	}

	@Override
	public List<Book> displayBooks() {
		List<Book> books = bookRepository.findAll();
		return books;
	}

	@Override
	public Book displaySingleBook(Long bookId , String token) throws BookException, UserException {
		Long Id = tokenUtils.decodeToken(token);
		Users users = userRepository.findById(Id)
				.orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "USER_NOT_FOUND_EXCEPTION_MESSAGE"));
		
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookException(HttpStatus.NOT_FOUND, "This Book does not exist..!"));
		return book;
	}

	@Override
	public Book updateBook(Long bookId, BookDto bookDto , String token) throws BookException, UserException {
		Long Id = tokenUtils.decodeToken(token);
		Users users = userRepository.findById(Id)
				.orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "USER_NOT_FOUND_EXCEPTION_MESSAGE"));
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookException(HttpStatus.NOT_FOUND, "This Book does not exist..!"));
		BeanUtils.copyProperties(bookDto, book);
		bookRepository.save(book);
		return book;
	}

	@Override
	public Book delete(Long bookId ,String token ) throws BookException, UserException {
		Long Id = tokenUtils.decodeToken(token);
		Users users = userRepository.findById(Id)
				.orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "USER_NOT_FOUND_EXCEPTION_MESSAGE"));
		bookRepository.deleteById(bookId);
		return null;
	}

}
