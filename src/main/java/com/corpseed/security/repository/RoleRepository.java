package com.corpseed.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corpseed.security.models.ERole;
import com.corpseed.security.models.Role;




@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
  
//  List<Role> findAllById(List<Long> ids);
}