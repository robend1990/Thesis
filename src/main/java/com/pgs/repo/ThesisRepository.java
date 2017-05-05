package com.pgs.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pgs.model.Thesis;

@Repository
@Transactional
public interface ThesisRepository extends JpaRepository<Thesis, Long>{
	Thesis findByStudentId(Long id);
}
