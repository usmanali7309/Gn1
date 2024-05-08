package com.avaya.vcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaya.vcc.entities.TestData;

@Repository
public interface TestDataRepository extends JpaRepository<TestData, Integer> {

}
