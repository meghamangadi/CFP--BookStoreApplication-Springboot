package com.bridgelabz.bookstoreapp.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreapp.entity.CartBooks;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartBooks, Long> {
    @Query(value = "SELECT * FROM cart where user_Id = :id", nativeQuery = true)
    List<CartBooks> findAllCartsByUserId(Long id);
}
