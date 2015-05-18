package com.mgo.amititz.endpoints.service;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class FileSystemImpl implements IFileSystem {

	public File getFile(String path) {
		return new File(path);
	}
}
