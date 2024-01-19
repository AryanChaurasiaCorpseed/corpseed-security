package com.corpseed.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureCorpController {
	  @GetMapping("/securityService/api/TmpTest")
//	  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	  public String userAccess(@RequestHeader(value="Authorization") String Authorization) {
	    return "User Content.";
	  }
}
