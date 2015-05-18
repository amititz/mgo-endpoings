package com.mgo.amititz.endpoints.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgo.amititz.endpoints.dto.FileInfoDto;

@Service
public class DirectoryServiceImpl implements IDirectoryService {

	private IFileSystem fileSystem;
	
	@Autowired
	public DirectoryServiceImpl(IFileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	@Override
	public List<FileInfoDto> getFilesInDirectory(String directoryPath) throws InvalidPathException {
		List<FileInfoDto> result = new ArrayList<FileInfoDto>();
		
		File directory = this.fileSystem.getFile(directoryPath);
		if (!directory.exists()) {
			throw new InvalidPathException(String.format("Input directory %s doesn't exist", directoryPath));
		}
		
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
			    if (file.isFile()) {
			    	FileInfoDto dto = new FileInfoDto(file.getName(), directoryPath);
			    	result.add(dto);
			    }
			}		
		}
		
		return result;
	}

}
