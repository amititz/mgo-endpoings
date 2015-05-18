package com.mgo.amititz.endpoints.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.mgo.amititz.endpoints.dto.FileInfoDto;

public class DirectoryServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFilesInDirectory() {
		
		final String path = "1/2/3";
		
		File files[] = new File[2];
		File file = Mockito.mock(File.class);
		when(file.exists()).thenReturn(true);
		when(file.isFile()).thenReturn(true);
		when(file.isDirectory()).thenReturn(false);
		files[0] = file;
		
		file = Mockito.mock(File.class);
		when(file.exists()).thenReturn(true);
		when(file.isFile()).thenReturn(true);
		when(file.isDirectory()).thenReturn(false);
		files[1] = file;

		File directory = Mockito.mock(File.class);
		when(directory.exists()).thenReturn(true);
		when(directory.isFile()).thenReturn(false);
		when(directory.isDirectory()).thenReturn(true);
		when(directory.listFiles()).thenReturn(files);

		IFileSystem fileSystem = Mockito.mock(IFileSystem.class);
		when(fileSystem.getFile(path)).thenReturn(directory);
		
		DirectoryServiceImpl service = new DirectoryServiceImpl(fileSystem);
		try {
			List<FileInfoDto> results = service.getFilesInDirectory("1/2/3");
			assertEquals(2, results.size());
		} catch (InvalidPathException e) {
			fail("Exception shouldn't be thrown here");
		}
	}

}
