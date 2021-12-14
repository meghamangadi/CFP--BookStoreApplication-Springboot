package com.bridgelabz.bookstoreapp.service;

import java.util.List;

import com.bridgelabz.bookstoreapp.dto.CartDto;
import com.bridgelabz.bookstoreapp.entity.CartBooks;
import com.bridgelabz.bookstoreapp.exception.BookException;
import com.bridgelabz.bookstoreapp.exception.UserException;

public interface CartService {
	CartBooks addToCart(String token,CartDto cartDTO) throws BookException, UserException;

    void deleteFromCart(Long cartId);

    CartBooks updateQuantity(String token, Long cartId, int quantity);

    List<CartBooks> findAllInCart(String token);
}
