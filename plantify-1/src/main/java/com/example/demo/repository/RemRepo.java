package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Remainders;

public interface RemRepo extends JpaRepository<Remainders, Integer> {

}
