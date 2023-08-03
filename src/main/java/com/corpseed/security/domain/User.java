package com.corpseed.security.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor(force = true)
@Table(name = "user")
public class User {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NonNull
//    private String firstName;
//
//    @NonNull
//    private String lastName;
//    
//
//    @NonNull
//    private String name;
//
//    @NonNull
//    private String email;
//
//    @NonNull
//    private String designation;
//
//    @NonNull
//    private String department;
//
//    private String password;
//    
//    private String token;
//
//    /**
//	 * @return the id
//	 */
//	public Long getId() {
//		return id;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	/**
//	 * @return the firstName
//	 */
//	public String getFirstName() {
//		return firstName;
//	}
//
//	/**
//	 * @param firstName the firstName to set
//	 */
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	/**
//	 * @return the lastName
//	 */
//	public String getLastName() {
//		return lastName;
//	}
//
//	/**
//	 * @param lastName the lastName to set
//	 */
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	
//	
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	/**
//	 * @return the email
//	 */
//	public String getEmail() {
//		return email;
//	}
//
//	/**
//	 * @param email the email to set
//	 */
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	/**
//	 * @return the designation
//	 */
//	public String getDesignation() {
//		return designation;
//	}
//
//	/**
//	 * @param designation the designation to set
//	 */
//	public void setDesignation(String designation) {
//		this.designation = designation;
//	}
//
//	/**
//	 * @return the department
//	 */
//	public String getDepartment() {
//		return department;
//	}
//
//	/**
//	 * @param department the department to set
//	 */
//	public void setDepartment(String department) {
//		this.department = department;
//	}
//
//	/**
//	 * @return the password
//	 */
//	public String getPassword() {
//		return password;
//	}
//
//	/**
//	 * @param password the password to set
//	 */
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}
//	
//	

	
}

