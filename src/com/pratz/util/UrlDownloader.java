package com.pratz.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;

import com.pratz.constant.AppConstants;
import com.pratz.exception.FileWritingExecption;

public class UrlDownloader {

	public File downloadUrl(String url){
		File uniqueFile = null;
		try {
			//get the url response as document
			Document doc = Jsoup.connect(url).get();
			
			//convert document to string
			String htmlContent = doc.html();
			
			File downloadDir = createDownloadDir();
			
			//creating unique file inside the download directory
			uniqueFile = File.createTempFile("main", ".html", downloadDir);
			
			AppUtils.writeTextToFile(htmlContent, uniqueFile);
			
		}catch(MalformedURLException e){
			System.err.println("The url is not proper" + e);
		}catch(HttpStatusException e){
			
		}catch(UnsupportedMimeTypeException e){
			
		}catch(SocketTimeoutException e){
			
		}catch(FileWritingExecption e){
			System.err.println("Error occurred while writing to file");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return uniqueFile;
	}
	
	private File createDownloadDir(){
		File downloadDir = new File(AppConstants.DOWNLOAD_DIRECTORY);
		if(!downloadDir.exists()){
			if(downloadDir.mkdirs()){
				//TODO log successful msg
			}else{
				//throw exception and exit
			}
		}
		return downloadDir;
	}
	
}
