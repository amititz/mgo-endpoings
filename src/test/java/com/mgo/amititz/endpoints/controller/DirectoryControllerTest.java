package com.mgo.amititz.endpoints.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mgo.amititz.endpoints.dto.FileInfoDto;
import com.mgo.amititz.endpoints.service.IDirectoryService;
import com.mgo.amititz.endpoints.service.InvalidPathException;

public class DirectoryControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDirectoryContentsEmpty() {
		
		final String path = "1/2/3";
		
		List<FileInfoDto> mockResults = new ArrayList<FileInfoDto>();
		IDirectoryService service = Mockito.mock(IDirectoryService.class);
		try {
			when(service.getFilesInDirectory(path)).thenReturn(mockResults);
		} catch (InvalidPathException e) {
		}

		DirectoryController controller = new DirectoryController(service);
		ResponseEntity<?> res = controller.getDirectoryContents(path);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		@SuppressWarnings("unchecked")
		List<FileInfoDto> results = (List<FileInfoDto>) res.getBody();
		assertEquals(0, results.size());
	}
	
	@Test
	public void testGetDirectoryContentsNull() {
		
		final String path = "1/2/3";
		
		IDirectoryService service = Mockito.mock(IDirectoryService.class);
		try {
			when(service.getFilesInDirectory(path)).thenReturn(null);
		} catch (InvalidPathException e) {
		}

		DirectoryController controller = new DirectoryController(service);
		ResponseEntity<?> res = controller.getDirectoryContents(path);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertNull(res.getBody());
	}
	
	
	@Test
	public void testGetDirectoryContentsSuccess() {
		
		final String path = "1/2/3";
		
		List<FileInfoDto> mockResults = new ArrayList<FileInfoDto>();
		mockResults.add(new FileInfoDto("file1", "1/2/3"));
		mockResults.add(new FileInfoDto("file2", "1/2/3"));
		IDirectoryService service = Mockito.mock(IDirectoryService.class);
		try {
			when(service.getFilesInDirectory(path)).thenReturn(mockResults);
		} catch (InvalidPathException e) {
		}

		DirectoryController controller = new DirectoryController(service);
		ResponseEntity<?> res = controller.getDirectoryContents(path);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		@SuppressWarnings("unchecked")
		List<FileInfoDto> results = (List<FileInfoDto>) res.getBody();
		assertEquals(2, results.size());
		assertEquals(mockResults.get(0), results.get(0));
		assertEquals(mockResults.get(1), results.get(1));
	}

	
	@Test
	public void testGetDirectoryContentsInvalidPath() {
		
		final String path = "1/2/3";
		
		List<FileInfoDto> mockResults = new ArrayList<FileInfoDto>();
		mockResults.add(new FileInfoDto("file1", "1/2/3"));
		mockResults.add(new FileInfoDto("file2", "1/2/3"));
		IDirectoryService service = Mockito.mock(IDirectoryService.class);
		try {
			when(service.getFilesInDirectory(path)).thenThrow(new InvalidPathException("error message"));
		} catch (InvalidPathException e) {
		}

		DirectoryController controller = new DirectoryController(service);
		ResponseEntity<?> res = controller.getDirectoryContents(path);
		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
		
		String body = (String) res.getBody();
		assertEquals("Error processing the request: error message", body);
	}
	
}
