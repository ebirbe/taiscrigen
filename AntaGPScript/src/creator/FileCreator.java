package creator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.LineVector;

public class FileCreator {
	
	// Fields
	private String extension = ".tmf";
	private String file_name = "script";
	private String folder = "/home/jaky/";
	
	// constructor
	public FileCreator() {
		// TODO Auto-generated constructor stub
	}
	
	// Set & Get
	
	public String getFile_name() {
		return file_name;
	}
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public void setFile_name(String fileName) {
		file_name = fileName;
	}
	public String getExtension() {
		return extension;
	}
	
	//methods
	public void createFile(LineVector linesBuffer) throws IOException{
		String url = this.folder + this.file_name + this.extension;
		File file = new File(url);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		for (String linea : linesBuffer) {
			bw.write(linea);
			bw.flush();
		}
	}
}
