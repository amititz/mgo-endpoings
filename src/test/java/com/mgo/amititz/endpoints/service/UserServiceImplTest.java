package com.mgo.amititz.endpoints.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.mgo.amititz.endpoints.dto.UserDto;
import com.mgo.amititz.endpoints.dto.UserGroupDto;
import com.mgo.amititz.endpoints.entity.UserEntity;
import com.mgo.amititz.endpoints.entity.UserEntityRepository;

public class UserServiceImplTest {


	@Before
	public void setUp() throws Exception {
	}
	

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testFindUsers() throws Exception {
		UserEntityRepository repository = Mockito.mock(UserEntityRepository.class);
		UserEntity johnEntity = new UserEntity();
		UserDto johnDto = new UserDto();

		IUserDtoConverter converter = createMockConverter(johnEntity, johnDto);
		IUserGrouping grouping = createMockUserGrouping();
		IFieldMapper fieldMapper = createMockFieldMapper(repository, "firstName", "John", johnEntity);
		
		IUserService service = new UserServiceImpl(repository, converter, grouping, fieldMapper);
		
		// this is the actual method call
		Collection<UserGroupDto> groups = null;
		try {
			groups = service.findUsers("firstName", "John", "city");
		} catch (InvalidFieldException e) {
			fail("UserServiceImpl shouldn't throw an exception");
		}
		
		assertEquals(1, groups.size());
		UserGroupDto group = groups.iterator().next();
		assertEquals("city", group.getGroupByFieldName());

		List<UserDto> users = group.getUsers();
		assertEquals(1, users.size());

		UserDto user = users.get(0);
		assertSame(johnDto, user);
	}
	
	private IFieldMapper createMockFieldMapper(UserEntityRepository repository, String fieldName, String fieldValue, UserEntity userEntity)
	{
		IFieldMapper fieldMapper = Mockito.mock(IFieldMapper.class);
		IUserRepositoryFindByFieldValue finderMethod = Mockito.mock(IUserRepositoryFindByFieldValue.class);
		when(finderMethod.find(repository, fieldValue)).thenReturn(Arrays.asList(userEntity));
		when(fieldMapper.getFinderMethod(fieldName)).thenReturn(finderMethod);
		return fieldMapper;
	}

	private IUserDtoConverter createMockConverter(UserEntity userEntity, UserDto userDto) {
		IUserDtoConverter converter = Mockito.mock(IUserDtoConverter.class);
		when(converter.convertUserEntity(userEntity)).thenReturn(userDto);
		return converter;
	}
	
	private IUserGrouping createMockUserGrouping() throws Exception {
		return createMockUserGrouping("");
	}
	
	private IUserGrouping createMockUserGrouping(String groupingFieldValue) throws Exception {
		IUserGrouping mockUserGrouping = Mockito.mock(IUserGrouping.class);
		when(mockUserGrouping.groupUsers(any(), anyString())).then(invocation -> {
			@SuppressWarnings("unchecked")
			List<UserDto> users = (List<UserDto>)invocation.getArgumentAt(0, List.class);
			String groupingField = invocation.getArgumentAt(1, String.class);
			return wrapInSingleGroup(users, groupingField, groupingFieldValue);
		});
		return mockUserGrouping;
	}


	private Collection<UserGroupDto> wrapInSingleGroup(List<UserDto> users, String groupingField, String groupingFieldValue) {
		List<UserGroupDto> result = new ArrayList<UserGroupDto>();
		UserGroupDto group = new UserGroupDto(groupingField, groupingFieldValue);
		for (UserDto user : users) {
			group.addUser(user);
		}
		result.add(group);
		return result;
	}
	
	
}
