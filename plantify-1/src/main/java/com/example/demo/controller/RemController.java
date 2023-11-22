package com.example.demo.controller;

import com.example.demo.model.Remainders;
import com.example.demo.service.RemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Remainders")
public class RemController {
	@Autowired
	RemService obj;
	
	@GetMapping("/get")
	public List<Remainders> getRems()
	{
		return obj.getd();
	}
	
	@PostMapping("/post")
	public void postRems(@RequestBody Remainders ob)
	{
		obj.postRem(ob);
	}
	
	@DeleteMapping("/delete")
	public void deleteRems(@RequestParam int id)
	{
		obj.deleteRem(id);
	}
	
	@PutMapping("/put")
	public void editRems(@RequestBody Remainders o)
	{
		obj.editRem(o);
	}
	
	

}
