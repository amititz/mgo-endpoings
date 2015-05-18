package com.mgo.amititz.endpoints.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.mgo.amititz.endpoints.dto.UserDto;
import com.mgo.amititz.endpoints.dto.UserGroupDto;

public class UserGroupingImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGroupUsers() {
		
		IUserFieldGetter cityFieldGetter = user -> user.getCity();
		IFieldMapper mapper = Mockito.mock(IFieldMapper.class);
		when(mapper.getFieldGetter("city")).thenReturn(cityFieldGetter);
		UserGroupingImpl grouping = new UserGroupingImpl(mapper);
		
		List<UserDto> users = new ArrayList<UserDto>();
		 
		users.add(createUser("Miles", "Davis", "New York"));
		users.add(createUser("Sonny", "Rollins", "New York"));
		users.add(createUser("Sonny", "Stitt", "Los Angeles"));
		
		try {
			Collection<UserGroupDto> results = grouping.groupUsers(users, "city");
			assertEquals(2, results.size());
			List<UserGroupDto> resultList = new ArrayList<UserGroupDto>(results);
			resultList.sort((g1, g2) -> g1.getGroupByFieldValue().compareTo(g2.getGroupByFieldValue()) );
			
			UserGroupDto group = resultList.get(0);
			assertEquals("city", group.getGroupByFieldName());
			assertEquals("Los Angeles", group.getGroupByFieldValue());

			group = resultList.get(1);
			assertEquals("city", group.getGroupByFieldName());
			assertEquals("New York", group.getGroupByFieldValue());

		} catch (InvalidFieldException e) {
			fail("Shouldn't throw exception");
		}
	}
	
	private UserDto createUser(String firstName, String lastName, String city) {
		UserDto userDto = new UserDto();
		userDto.setFirstName(firstName);
		userDto.setLastName(lastName);
		userDto.setCity(city);
		return userDto;
	}

}
