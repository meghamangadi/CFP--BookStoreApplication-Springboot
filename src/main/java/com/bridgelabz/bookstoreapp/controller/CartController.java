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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.CartDto;
import com.bridgelabz.bookstoreapp.entity.CartBooks;
import com.bridgelabz.bookstoreapp.exception.BookException;
import com.bridgelabz.bookstoreapp.exception.UserException;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.response.Response;
import com.bridgelabz.bookstoreapp.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    CartRepository cartRepo;

    @PostMapping("/add")
    ResponseEntity<Response> addToCart(@RequestHeader(name = "token") String token, @RequestBody CartDto cartDTO) throws BookException, UserException {
    	CartBooks add = cartService.addToCart(token, cartDTO);
    	return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "Book added to cart successfully",  add));
		
        }

    @DeleteMapping("/remove/{cartId}")
    ResponseEntity<Response> removeFromCart(@PathVariable("cartId") Long cartId) {
        cartService.deleteFromCart(cartId);
        return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "Book deleted from cart successfully ",  cartId));
       
    }

    @PutMapping("/update/{cartId}/{quantity}")
    ResponseEntity<Response> updateCart(@RequestHeader(name = "token") String token, @PathVariable("cartId") Long cartId,
                                           @PathVariable("quantity") int quantity) {
    	CartBooks cart = cartService.updateQuantity(token, cartId, quantity);
    	   return ResponseEntity.ok()
   				.body(new Response(HttpStatus.ACCEPTED, "Book updated From Cart successfully",  cart));
        
    }

    @GetMapping("/get")
    ResponseEntity<Response> findAllCartsByUser(@RequestHeader(name = "token") String token) {
        List<CartBooks> allItemsForUser = cartService.findAllInCart(token);
        
        return ResponseEntity.ok()
   				.body(new Response(HttpStatus.ACCEPTED, "All Books in Cart fetched for user successfully",  allItemsForUser));
         
    }

    @GetMapping("/getAll")
    ResponseEntity<Response> findAllCarts() {
        List<CartBooks> allItems = cartRepo.findAll();
        
        return ResponseEntity.ok()
   				.body(new Response(HttpStatus.ACCEPTED, "All Books in Cart fetched successfully ",  allItems));
       
    }
}
