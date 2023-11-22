package com.example.demo.service;

import com.example.demo.model.Remainders;
import com.example.demo.repository.RemRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class RemService {

	@Autowired
	RemRepo rp;
	public List<Remainders> getd()
	{
		return rp.findAll();
	}
	
	public void postRem(Remainders obj)
	{
		rp.save(obj);
	}
	
	public void deleteRem(int id)
	{
		rp.deleteById(id);
	}
	
	public void editRem(Remainders obj)
	{
		rp.save(obj);
	}
	
}
