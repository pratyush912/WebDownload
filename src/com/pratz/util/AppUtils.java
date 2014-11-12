package com.pratz.util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.pratz.exception.FileWritingExecption;

public class AppUtils {
	
	/**
	 * Writes the provided text to the file provided
	 * 
	 * @param text string to be written to a file
	 * @param file the file where the text has to be written
	 * @throws IOException 
	 */
	public static void writeTextToFile(String text, File file) throws IOException {
		if(file!=null){
			if(!file.exists()){
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdirs();
				}
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.err.println("Error occurred while creating file");
					throw e;
				}
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(file));){
					bw.write(text);	
				} catch (IOException e) {
					System.err.println("Error occurred while writing to file file");
					throw new FileWritingExecption(e);
				}
			}
		}
	}
	
	public static File downloadFile(String url, String filePath, File parent) throws IOException{
		File file = null;
		if(url!=null && filePath != null){
			if(parent!=null){
				file = new File(parent.getAbsolutePath()+filePath);	
			}else{
				file = new File(filePath);
			}
			
			if(!file.exists()){
				File parentDir = file.getParentFile();
				if(!parentDir.exists()){
					if(!parentDir.mkdirs()){
						throw new IOException("Unable to create directory structure");
					}
				}
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.err.println("Error occurred while creating file");
					throw e;
				}
				URL u = new URL(url);
				try(InputStream is = u.openStream();
						BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))){
					int b;
					while((b= is.read())!=-1){
						bos.write(b);
					}
				}
			}
		}
		return file;
	}
	
	/**
	 * Creates unique directory
	 * 
	 * @return a file object 
	 * @throws IOException
	 */
	public static File createUniqueDir() throws IOException{
		
		File downloadDir = new File("download");
		
		//checking and creating the download directory 
		if(!downloadDir.exists()){
			if(!downloadDir.mkdir()){
				throw new IOException("Could not create the download directory" + downloadDir.getAbsolutePath());
			}
		}
		
		//creating a unique file inside download directory
		File unique = File.createTempFile("download", Long.toString(System.nanoTime()), downloadDir);
		
		//deleting the unique file
		if(!(unique.delete())){
	        throw new IOException("Could not delete temp file: " + unique.getAbsolutePath());
	    }

		//creating the unique directory 
	    if(!(unique.mkdir())){
	        throw new IOException("Could not create temp directory: " + unique.getAbsolutePath());
	    }
	    
	    return unique;
	}
	
	

}
