package com.bridgelabz.bookstoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.bookstoreapp.entity.Book;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long>{

}
