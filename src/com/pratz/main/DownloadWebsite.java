package com.pratz.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;

import com.pratz.parser.HtmlParser;
import com.pratz.task.model.AppImage;
import com.pratz.task.model.Carrier;
import com.pratz.util.AppUtils;
import com.pratz.util.SourceIdentifier;
import com.pratz.util.UrlDownloader;
import com.pratz.util.SourceIdentifier.SourceType;

public class DownloadWebsite {
	
	private DownloadWebsite() {
		// 
	}
	
	
	/**
	 * Downloads a provided url recursively.<br>
	 * If parent directory is provided then all the website content goes in the parent directory
	 * 
	 * @param parentDir
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static File download(File parentDir, String url) throws IOException {
		return download(parentDir, url,null);
	}
	
	public static File download(File parentDir, String url, String filePath) throws IOException {
		UrlDownloader urlDownload = new UrlDownloader();
		Document document = urlDownload.downloadUrl(url);
		
		if(parentDir==null){
			parentDir = AppUtils.createUniqueDir();
		}
		
		urlDownload.writeToFile(parentDir,filePath);
		
		Carrier carrier = HtmlParser.parseHTML(document);
		
		List<AppImage> cssUrls = carrier.getCssUrls();
		List<AppImage> jsUrls = carrier.getJsUrls();
		List<AppImage> imgUrls = carrier.getImageUrls();
		List<AppImage> otherUrls = carrier.getOtherUrls();
		
		downloadFiles(cssUrls,parentDir);
		downloadFiles(jsUrls,parentDir);
		downloadFiles(imgUrls,parentDir);
		downloadFiles(otherUrls,parentDir);
		
		return parentDir;
	}
	
	private static void downloadFileRecursively(List<AppImage> fileUrls, File parentDir) {
		if(!fileUrls.isEmpty()){
			for(AppImage url : fileUrls){
				try {
					
					modifyUrl(url);
//					AppUtils.downloadFile(url.getDownloadUrl(), url.getStoreUrl(), parentDir);
					DownloadWebsite.download(parentDir, url.getDownloadUrl());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void downloadFiles(List<AppImage> fileUrls, File parentDir) {
		if(!fileUrls.isEmpty()){
			for(AppImage url : fileUrls){
				try {
					
					modifyUrl(url);
					AppUtils.downloadFile(url.getDownloadUrl(), url.getStoreUrl(), parentDir);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private static void modifyUrl(AppImage url) {
		switch (SourceIdentifier.identifySource(url.getStoreUrl())) {
		case DIRECTORY:
			url.setStoreUrl(url.getStoreUrl()+ "index.html");
			break;
		case ID:
			url.setStoreUrl(url.getStoreUrl().replace("#","/"));
			break;
		case FILE:
			if(!url.getStoreUrl().startsWith("/")){
				url.setStoreUrl("/"+url.getStoreUrl());
			}
			break;
		default:
			break;
		}
	}

}
