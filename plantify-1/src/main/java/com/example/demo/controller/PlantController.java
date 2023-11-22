package com.example.demo.controller;


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

import com.example.demo.request.PlantRequest;
import com.example.demo.response.PlantResponse;
import com.example.demo.service.PlantService;

@RestController
@RequestMapping("/api/plants")
@CrossOrigin(origins = "http://localhost:3000")
public class PlantController {
	
	@Autowired
	PlantService ps;
	
	@GetMapping("/get")
	public List<PlantResponse> getAllPlants()
	{
		return ps.getAllPlants();
	}
	
	@PostMapping("/post")
	public void SaveAllData(@RequestBody PlantRequest p)
	{
		ps.savePlant(p);
	}
	@PutMapping("/put")
	public void UpdateAllData(PlantRequest p, int id)
	{
		ps.updatePlant(p,id);
	}
	@DeleteMapping("/delete")
	public void DeleteDataById(@RequestParam int id)
	{
		ps.deletePlant(id);
	}
}