package com.corpseed.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.corpseed.security.models.ERole;
import com.corpseed.security.models.Role;




@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
     Optional<Role> findByName(ERole name);
	 @Query(value = "SELECT * FROM roles r WHERE r.name in(:strRoles)", nativeQuery = true)
     List<Role> findAllByNameIn(List<String> strRoles);
  
//  List<Role> findAllById(List<Long> ids);
}