package com.pratz.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;

public class UrlDownloader {

	private Document doc;

	public Document downloadUrl(String url){
		try {
			//get the url response as document
			doc = Jsoup.connect(url).get();
		}catch(MalformedURLException e){
			System.err.println("The url is not proper" + e);
		}catch(HttpStatusException e){
			
		}catch(UnsupportedMimeTypeException e){
			
		}catch(SocketTimeoutException e){
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return doc;
	}
	
	public void writeToFile(File downloadDir){
		//convert document to string
		String htmlContent = doc.html();

		//creating unique file inside the download directory
		File uniqueFile  = null;
		try{
			uniqueFile= File.createTempFile("main", ".html", downloadDir);
			AppUtils.writeTextToFile(htmlContent, uniqueFile);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
