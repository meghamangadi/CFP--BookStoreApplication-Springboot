package com.bridgelabz.bookstoreapp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cart")
@Data
 
@AllArgsConstructor
public class CartBooks {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "userId")
	    private Users user;

	    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "bookId")
	    private Book  book;

	    private int quantity;


	    public CartBooks(Users user, Book  book, int quantity) {
	        this.user = user;
	        this.book = book;
	        this.quantity = quantity;
	    }

	    public CartBooks() {

	    }
}
