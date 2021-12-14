package com.bridgelabz.bookstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.BookDto;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Users;
import com.bridgelabz.bookstoreapp.exception.BookException;
import com.bridgelabz.bookstoreapp.exception.UserException;
import com.bridgelabz.bookstoreapp.response.Response;
import com.bridgelabz.bookstoreapp.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/addbook/{token}")
	public ResponseEntity<Response> addBook(@RequestBody(required = true) BookDto dto , @PathVariable("token") String token ) throws UserException {
		Book addBook = bookService.addBook(dto , token);

		return ResponseEntity.ok().body(new Response(HttpStatus.ACCEPTED, "book added  successfully", addBook));

	} 
	
	@PutMapping(value = "/updatebook/{token}")
	public ResponseEntity<Response> updatebook(@RequestBody(required = true) BookDto dto, @RequestParam Long bookId , @PathVariable("token") String token)
			throws Exception {
		Book addBook = bookService.updateBook(bookId, dto, token);

		return ResponseEntity.ok().body(new Response(HttpStatus.ACCEPTED, "book updated  successfully", addBook));

	}

	@GetMapping(value = "/getBookById/{token}")
	public ResponseEntity<Response> getBook(@RequestParam Long bookId , @PathVariable("token") String token) throws Exception {
		Book addBook = bookService.displaySingleBook(bookId , token);

		return ResponseEntity.ok().body(new Response(HttpStatus.ACCEPTED, "Book retrived successfully", addBook));

	}
	@GetMapping(value = "/getAllBook")
	public ResponseEntity<Response> getAllBook() throws Exception {
		List<Book> addBook = bookService.displayBooks();

		return ResponseEntity.ok().body(new Response(HttpStatus.ACCEPTED, "Book retrived successfully", addBook));

	}
	@DeleteMapping("/delete/{token}")
	public ResponseEntity<Response>delete(@RequestParam Long bookId , @PathVariable("token") String token) throws BookException, UserException {
		Book book= bookService.delete(bookId , token);
		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "Book deleted successfully", book));
		
	}


}
