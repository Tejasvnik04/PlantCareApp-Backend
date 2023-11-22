package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PlantEntity;

public interface PlantRepo extends JpaRepository<PlantEntity, Integer> {
 public List<PlantEntity> findByUserId(Long id);
}
