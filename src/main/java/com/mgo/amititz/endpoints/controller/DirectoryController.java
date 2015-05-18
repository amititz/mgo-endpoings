package com.mgo.amititz.endpoints.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgo.amititz.endpoints.dto.FileInfoDto;
import com.mgo.amititz.endpoints.service.IDirectoryService;
import com.mgo.amititz.endpoints.service.InvalidPathException;

@RestController
public class DirectoryController {

	private IDirectoryService directoryService;
	
	@Autowired
	public DirectoryController(IDirectoryService directoryService) {
		this.directoryService = directoryService;
	}

	
	@RequestMapping(value = "/endpoints/1.0/dir", method=RequestMethod.GET)
	public ResponseEntity<?> getDirectoryContents(
			@RequestParam(value="path") String path) {
		
		try {
			List<FileInfoDto> result = directoryService.getFilesInDirectory(path);
			return new ResponseEntity<>(result, HttpStatus.OK); 
		} catch (InvalidPathException e) {
			String errorMessage = String.format("Error processing the request: %s", e.getMessage());
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
