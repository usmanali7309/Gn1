package com.avaya.vcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaya.vcc.entities.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

}
