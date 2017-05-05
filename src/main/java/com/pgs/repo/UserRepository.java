package com.pgs.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pgs.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
	@Query("select u from User u join fetch u.role r left join fetch u.specialization s where u.email = :email ")
	User findByEmail(@Param("email") String email);
	
	@Query("select u from User u join fetch u.role r left join fetch u.specialization s where u.albumNumber = :album_number ")	
	User findByAlbumNumber(@Param("album_number") Integer albumNumber);
	
	@Query("select u from User u join fetch u.role r left join fetch u.specialization s")
	List<User> queryAll();
}
