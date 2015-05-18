package com.mgo.amititz.endpoints.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mgo.amititz.endpoints.dto.UserDto;
import com.mgo.amititz.endpoints.dto.UserGroupDto;

@Component
public class UserGroupingImpl implements IUserGrouping {

	private IFieldMapper fieldMapper;
	
	@Autowired
	public UserGroupingImpl(IFieldMapper fieldMapper) {
		this.fieldMapper = fieldMapper;
	}

	@Override
	public Collection<UserGroupDto> groupUsers(List<UserDto> users, String groupingField) throws InvalidFieldException {
		Map<String, UserGroupDto> groupingMap = new HashMap<String, UserGroupDto>();
		
		IUserFieldGetter fieldGetter = getFieldGetter(groupingField);

		for (UserDto user : users) {
			String fieldValue = fieldGetter.getFieldValue(user);
			UserGroupDto group = groupingMap.get(fieldValue);
			if (group == null) {
				group = new UserGroupDto(groupingField, fieldValue);
				groupingMap.put(fieldValue, group);
			}
			group.addUser(user);
		}
		return groupingMap.values();
	}

	private IUserFieldGetter getFieldGetter(String groupingField) throws InvalidFieldException {
		IUserFieldGetter fieldGetter = null;

		if (groupingField == null || groupingField.isEmpty()) {
			fieldGetter = user -> "";
		} else {
			fieldGetter = fieldMapper.getFieldGetter(groupingField);
			if (fieldGetter == null) {
				throw new InvalidFieldException(String.format("Unrecognized grouping field: %s", groupingField));
			}
		}
		return fieldGetter;
		
	}
	
}
