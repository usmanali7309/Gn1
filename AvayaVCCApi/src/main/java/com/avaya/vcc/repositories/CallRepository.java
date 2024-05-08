package com.avaya.vcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaya.vcc.entities.Call;

@Repository
public interface CallRepository extends JpaRepository<Call, String> {

}
