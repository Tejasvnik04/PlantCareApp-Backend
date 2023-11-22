package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;

@RestController
public class AdminController {
 @Autowired
 AuthService obj;
 
 @GetMapping("/getusers")
 public List<Object> getUsers(){
	 return obj.getd();
 }
}
