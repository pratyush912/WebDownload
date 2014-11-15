package com.pratz.util;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *  This class is used to download the url and write in the provided location
 *
 */
public class UrlDownloader {

	private Document doc;

	/**
	 * 
	 * Download the url if an html else throws {@link IOException } 
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Document downloadUrl(String url) throws IOException{
		//get the url response as document
		return	doc = Jsoup.connect(url).get();
	}
	
	
	/**
	 * Write the html to the given directory and file path
	 * @param downloadDir
	 * @param filePath
	 */
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
