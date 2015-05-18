package com.mgo.amititz.endpoints.data;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mgo.amititz.endpoints.entity.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserEntityTest {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void testUserPersistence() {
		UserEntity user = new UserEntity();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setEmailAddress("John.Doe@gmail.com");
		user.setUserName("Johndoe");
		user.setPassword("123");
		
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		
		assertEquals("John", user.getFirstName());
		assertEquals("Doe", user.getLastName());
		assertEquals("John.Doe@gmail.com", user.getEmailAddress());
		assertEquals("Johndoe", user.getUserName());
		assertEquals("123", user.getPassword());
	}

}
