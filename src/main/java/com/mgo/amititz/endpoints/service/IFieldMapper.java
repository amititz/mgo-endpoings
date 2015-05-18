package com.mgo.amititz.endpoints.service;


interface IFieldMapper {
	IUserFieldGetter getFieldGetter(String fieldName);
	IUserRepositoryFindByFieldValue getFinderMethod(String fieldName);
}