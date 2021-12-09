package com.bridgelabz.bookstoreapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreapp.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	
	@Query(value = "select * from Users where email=?", nativeQuery = true)
	Optional<Users> FindByEmail(String email);
	
	@Query(value = "update Users set password=? where user_id=?", nativeQuery = true)
	void updateUserPassword(String password, Long id);
}
