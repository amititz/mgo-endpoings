package com.mgo.amititz.endpoints.dto;

public class FileInfoDto {
	private String fileName;
	private String parentPath;
		
	
	public FileInfoDto(String fileName, String parentPath) {
		this.fileName = fileName;
		this.parentPath = parentPath;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	@Override
	public int hashCode() {
		return fileName.hashCode() ^ parentPath.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileInfoDto other = (FileInfoDto) obj;
		return fileName.equals(other.fileName) && parentPath.equals(other.parentPath);
	}

		
}
