package com.bridgelabz.bookstoreapp.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.CartDto;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.CartBooks;
import com.bridgelabz.bookstoreapp.entity.Users;
import com.bridgelabz.bookstoreapp.exception.BookException;
import com.bridgelabz.bookstoreapp.exception.UserException;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.service.BookService;
import com.bridgelabz.bookstoreapp.service.CartService;
import com.bridgelabz.bookstoreapp.utils.TokenUtils;

@Service
public class CartServiceImplementation implements CartService  {
  

    @Autowired
    BookService  bookService;

    @Autowired
    CartRepository cartRepo;

    @Autowired
    TokenUtils tokenUtil;

    @Autowired
    UserRepository userRepo;

    @Override
    public CartBooks addToCart(String token ,CartDto cartDTO) throws BookException, UserException {
    	Long id = tokenUtil.decodeToken(token);
        Optional<Users> user = userRepo.findById(id);
        if(user.isPresent()) {
            Book  book = bookService.displaySingleBook(cartDTO.bookId, token);
            		 
            CartBooks cart = new CartBooks(user.get(), book, cartDTO.quantity);
            return cartRepo.save(cart);
        }
        return null;
    }

    @Override
    public void deleteFromCart(Long cartId) {
        cartRepo.deleteById(cartId);
         
    }

    @Override
    public CartBooks updateQuantity(String token, Long cartId, int quantity) {
    	Long id = tokenUtil.decodeToken(token);
        Optional<Users> isPresent = userRepo.findById(id);
        if(isPresent.isPresent()){
        	CartBooks cart = cartRepo.getById(cartId);
            cart.setQuantity(quantity);
           return cartRepo.save(cart);
        }
        return  null;
    }

    @Override
    public List<CartBooks> findAllInCart(String token) {
    	Long id = tokenUtil.decodeToken(token);
        Optional<Users> isPresent = userRepo.findById(id);
        if(isPresent.isPresent()){
            List<CartBooks> cartItems = cartRepo.findAllCartsByUserId(id);
            return  cartItems;
        }
        return null;
    }

	@Override
	public void deleteAll(String token) {
		Long id = tokenUtil.decodeToken(token);
        Optional<Users> isPresent = userRepo.findById(id);
        if(isPresent.isPresent()){
            cartRepo.deleteAll();
   
        }
       
		
	}

 

}
