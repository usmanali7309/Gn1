package com.ms.laptop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.laptop.dto.LaptopDto;
import com.ms.laptop.entities.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
  
	public Optional<Laptop> findByEmpId(int empId);
}
