package com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}