package com.corpseed.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.corpseed.security.models.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	User findByEmail(String email);

	@Query(value = "SELECT * FROM users u WHERE u.email =:email and u.is_deleted =:b", nativeQuery = true)
	User findByEmailAndIsDeleted(String email,boolean b);
}