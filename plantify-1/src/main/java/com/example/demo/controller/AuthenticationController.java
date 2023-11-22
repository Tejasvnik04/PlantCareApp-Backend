package com.example.demo.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.constant.*;
import com.example.demo.request.AuthenticationRequest;
import com.example.demo.request.RegisterRequest;
import com.example.demo.response.AuthenticationResponse;
import com.example.demo.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Api.AUTH)
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController 
{
	
	private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest request) 
  {
      boolean isRegistered = authService.userRegistration(request);
      return isRegistered ? ResponseEntity.ok("User registered successfully")
              : ResponseEntity.badRequest().body("Something went wrong!");
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
      return ResponseEntity.ok(authService.userAuthentication(request));
  }
  
  
}

