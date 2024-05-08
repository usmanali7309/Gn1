package com.avaya.vcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaya.vcc.entities.Cdr;

@Repository
public interface CdrRepository extends JpaRepository<Cdr, Integer> {

}
