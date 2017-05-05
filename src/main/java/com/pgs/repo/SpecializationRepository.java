package com.pgs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pgs.model.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long>{

}
