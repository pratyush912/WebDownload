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
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.err.println("Error occurred while creating file");
					throw e;
				}
			}
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(file));){
				bw.write(text);	
			} catch (IOException e) {
				System.err.println("Error occurred while writing to file file");
				throw new FileWritingExecption(e);
			}
		}
	}
	
	public static File downloadFile(String url, String filePath) throws IOException{
		File file = null;
		if(url!=null && filePath != null){
			file = new File(filePath);
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
		return file;
	}
	
	

}
