package com.pgs;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.pgs.model.Role;
import com.pgs.model.Specialization;
import com.pgs.model.Thesis;
import com.pgs.model.User;
import com.pgs.repo.RoleRepository;
import com.pgs.repo.SpecializationRepository;
import com.pgs.repo.ThesisRepository;
import com.pgs.repo.UserRepository;
import com.pgs.utils.PasswordHasher;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(classes = {DataSoruceConfig.class, JpaConfig.class})
public class UserRepoTest {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired RoleRepository roleRepo;
	
	@Autowired
	SpecializationRepository specRepo;
	
	@Autowired
	ThesisRepository thesisRepo;
	
	@Test
	public void findAll(){
		userRepo.findAll();
		userRepo.findByEmail("admin");
	}
	
	@Test
	public void testBCrypt(){
		User user = userRepo.findByEmail("admin");
		Assert.assertTrue(BCrypt.checkpw("admin", user.getPassword()));
	}
	
	@Test
	public void save(){
		User user = new User();
		user.setUsername("admin");
		user.setPassword(PasswordHasher.hash("admin"));
		user.setEmail("admin");
		Role adminRole = roleRepo.findOne(new Long(1));
		user.setRole(adminRole);
		userRepo.save(user);
	}
	
	@Test
	public void saveSpecialization(){
		Specialization specialization = new Specialization();
		specialization.setName("test");
		specialization.setDegree("Inż");
		specialization.setProcess("Dzienne");
		specRepo.save(specialization);
	}
	
	@Test
	public void insertTimestamp(){
		Thesis thesis = thesisRepo.findOne(new Long(1));
		Timestamp ss = new Timestamp(new Date().getTime()); 
		Long dd =new Long(Long.valueOf("1433445120000"));
		Timestamp sdasd = new Timestamp(dd);
		thesis.setDefenseDate(ss);
		thesisRepo.save(thesis);
	}
}
