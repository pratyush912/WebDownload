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
			e.printStackTrace();
		}catch(UnsupportedMimeTypeException e){
			e.printStackTrace();
		}catch(SocketTimeoutException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return doc;
	}
	
	public void writeToFile(File downloadDir, String filePath){
		//convert document to string
		String htmlContent =null;
		htmlContent = doc.html();

		File mainFile  = null;
		try{
			if(filePath==null){
				filePath = downloadDir.getAbsolutePath()+"/index.html";
			}else{
				filePath = downloadDir.getAbsolutePath() + filePath;
			}
			mainFile= new File(filePath);
			AppUtils.writeTextToFile(htmlContent, mainFile);
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
