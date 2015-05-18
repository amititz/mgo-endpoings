package com.mgo.amititz.endpoints.service;

import java.util.List;

import com.mgo.amititz.endpoints.dto.FileInfoDto;

public interface IDirectoryService {
	List<FileInfoDto> getFilesInDirectory(String directoryPath) throws InvalidPathException;
}
