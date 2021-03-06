package com.mgo.amititz.endpoints.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mgo.amititz.endpoints.dto.UserDto;
import com.mgo.amititz.endpoints.dto.UserGroupDto;
import com.mgo.amititz.endpoints.entity.UserEntity;
import com.mgo.amititz.endpoints.entity.UserEntityRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Component
	private static class FieldMapper implements IFieldMapper {
		private static Map<String, IUserFieldGetter> fieldGetters = new HashMap<String, IUserFieldGetter>();
		private static Map<String, IUserRepositoryFindByFieldValue> findMethods = new HashMap<String, IUserRepositoryFindByFieldValue>();

		// TODO: we can create a configurable mapping from user input to these names based on configuration
		private static final String FIRST_NAME = "firstName";
		private static final String LAST_NAME = "lastName";
		private static final String CITY = "city";
		private static final String STATE = "state";
		
		static  {
			
			fieldGetters.put(FIRST_NAME, u -> u.getFirstName());
			fieldGetters.put(LAST_NAME, u -> u.getLastName());
			fieldGetters.put(CITY, u -> u.getCity());
			fieldGetters.put(STATE, u -> u.getState());
			
			findMethods.put(FIRST_NAME, (r, v) -> r.findByFirstName(v));
			findMethods.put(LAST_NAME, (r, v) -> r.findByLastName(v));
			findMethods.put(CITY, (r, v) -> r.findByCity(v));
			findMethods.put(STATE, (r, v) -> r.findByState(v));
		}

		public IUserFieldGetter getFieldGetter(String fieldName) {
			return fieldGetters.get(fieldName);
		}
		
		public IUserRepositoryFindByFieldValue getFinderMethod(String fieldName) {
			return findMethods.get(fieldName);
		}
	}
	
	
	private UserEntityRepository entityRepository;
	private IUserDtoConverter entityConverter;
	private IUserGrouping userGrouping;
	private IFieldMapper fieldMapper;
	
	
	
	@Autowired
	public UserServiceImpl(UserEntityRepository entityRepository, IUserDtoConverter entityConverter, IUserGrouping userGrouping, IFieldMapper fieldMapper) {
		this.entityRepository = entityRepository;
		this.entityConverter = entityConverter;
		this.userGrouping = userGrouping;
		this.fieldMapper = fieldMapper;
	}
	

	/* (non-Javadoc)
	 * @see com.mgo.amititz.endpoints.service.IUserRepository#findAll()
	 */
	@Override
	public Collection<UserDto> findAll() {
		return convertEntityList(this.entityRepository.findAll());
	}
	
	@Override
	public UserDto findByUserName(String userName) {
		return this.entityConverter.convertUserEntity(
				this.entityRepository.findByUserName(userName));
	}
	
	@Override
	public Collection<UserGroupDto> findUsers(String filterField, String filterValue, String groupingField) throws InvalidFieldException {
		
		// find the repository finder method based on the filter field, and call it
		IUserRepositoryFindByFieldValue finderMethod = getFinderMethod(filterField);
		List<UserEntity> userEntityList = finderMethod.find(this.entityRepository, filterValue);
		return groupUsers(userEntityList, groupingField);
	}

	
	@Override
	public Collection<UserGroupDto> findUsers(String filterField, String filterValue, String groupingField, int pageNum, int pageSize) throws InvalidFieldException {
		
		// To save time, in this overload only, I'm always finding by State. The non-paged version uses
		// the filterField to determine the find method to call on the repository. 
		Page<UserEntity> page = this.entityRepository.findByState(filterValue, new PageRequest(pageNum, pageSize));
		List<UserEntity> userEntityList = page.getContent(); 
		return groupUsers(userEntityList, groupingField);
	}
	
	private Collection<UserGroupDto> groupUsers(List<UserEntity> userEntityList, String groupingField) throws InvalidFieldException {
		if (userEntityList.size() > 0) {
			List<UserDto> userDtoList = convertEntityList(userEntityList);
			return this.userGrouping.groupUsers(userDtoList, groupingField);
		}
		return new ArrayList<UserGroupDto>();
	}
	
	private IUserRepositoryFindByFieldValue getFinderMethod(String filterField) throws InvalidFieldException {
		IUserRepositoryFindByFieldValue finderMethod = null;
		if (filterField == null || filterField.isEmpty()) {
			finderMethod = (repository, fieldValue) -> repository.findAll();
		} else {
			finderMethod = this.fieldMapper.getFinderMethod(filterField);
			if (finderMethod == null) {
				throw new InvalidFieldException(String.format("Unrecognized filter field: %s", filterField));
			}
		}
		return finderMethod;
	}


	private List<UserDto> convertEntityList(List<UserEntity> entityList) {
		List<UserDto> result = new ArrayList<UserDto>(entityList.size());
		for (UserEntity userEntity : entityList) {
			result.add(this.entityConverter.convertUserEntity(userEntity));
		}
		return result;
	}


}
