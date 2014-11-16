package com.pratz.main;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;

import com.pratz.parser.HtmlParser;
import com.pratz.task.model.ExtractedFile;
import com.pratz.task.model.Carrier;
import com.pratz.util.AppUtils;
import com.pratz.util.SourceIdentifier;
import com.pratz.util.UrlDownloader;

/**
 * This class is used to download a given url and files present in the url recursively
 * 
 * <br>
 * 
 * <b>Please note</b> It does not download the external urls
 */
public class DownloadWebsite {
	
	/**
	 * A set which maintains download file paths
	 */
	private Set<String> downloadedFiles;
	/**
	 * The root directory
	 */
	private File parentDir;
	/**
	 * The url to be downloaded
	 */
	private String url;
	/**
	 * File path extracted from the url
	 */
	private String filePath;
	
	/**
	 * depth for the download
	 */
	private int depth;
	
	/**
	 * Takes a url as an input
	 * 
	 * @param url
	 */
	public DownloadWebsite(String url){
		this(null,null,url,null);
	}
	
	public DownloadWebsite(String url, int depth){
		this(null,null,url,null,depth);
	}

	
	/**
	 * Private constructor used internally
	 * 
	 * @param downloadedFiles
	 * @param parentDir
	 * @param url
	 * @param filePath
	 */
	private DownloadWebsite(Set<String> downloadedFiles, File parentDir, String url, String filePath) {
		this(downloadedFiles, parentDir, url, filePath, -1);
	}
	
	private DownloadWebsite(Set<String> downloadedFiles, File parentDir, String url, String filePath, int depth){
		if(downloadedFiles==null){
			downloadedFiles = new HashSet<>();
		}
		this.downloadedFiles = downloadedFiles;
		this.parentDir = parentDir;
		this.url = url;
		this.filePath = filePath;
		this.depth  = depth;
	}
	
	/**
	 * Downloads the provided url in the contructor recursively
	 * 
	 * @return
	 * @throws IOException
	 */
	public File download() throws IOException {
		UrlDownloader urlDownload = new UrlDownloader();
		Document document = urlDownload.downloadUrl(url);
		
		if(document!=null){
		
			if(parentDir==null){
				//creating a new unique directory
				parentDir = AppUtils.createUniqueDir();
			}

			urlDownload.writeToFile(parentDir,filePath);
			
			if(filePath!=null){
				downloadedFiles.add(parentDir+filePath);	
			}else{
				downloadedFiles.add(parentDir+"/index.html");
			}
			
			
			Carrier carrier = HtmlParser.parseHTML(document);

			List<ExtractedFile> cssUrls = carrier.getCssUrls();
			List<ExtractedFile> jsUrls = carrier.getJsUrls();
			List<ExtractedFile> imgUrls = carrier.getImageUrls();
			List<ExtractedFile> otherUrls = carrier.getOtherUrls();

			downloadFiles(cssUrls,parentDir);
			downloadFiles(jsUrls,parentDir);
			downloadFiles(imgUrls,parentDir);
			
			
			//decrement depth in every iteration
			if(depth>-1){
				depth--;
				if(depth<0){
					// returning... not downloading files present in the link
					return parentDir;
				}
			}
			
			downloadFileRecursively(otherUrls,parentDir);

		}
		
		return parentDir;
	}
	
	/**
	 * downloads htmls files present inside the parent url
	 * 
	 * @param fileUrls
	 * @param parentDir
	 */
	private void downloadFileRecursively(List<ExtractedFile> fileUrls, File parentDir) {
		if(!fileUrls.isEmpty()){
			for(ExtractedFile url : fileUrls){
				try {
					if(modifyUrl(url)){
						File downloadFile = new File(parentDir.getAbsolutePath()+url.getStoreUrl());
						if(downloadFile.exists()){
							continue;
						}
						DownloadWebsite downWeb = new DownloadWebsite(downloadedFiles, parentDir, url.getDownloadUrl(), url.getStoreUrl(),depth);
						downWeb.download();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * downloads images/css/js files present inside the parent url
	 * 
	 * @param fileUrls
	 * @param parentDir
	 */
	private void downloadFiles(List<ExtractedFile> fileUrls, File parentDir) {
		if(!fileUrls.isEmpty()){
			for(ExtractedFile url : fileUrls){
				try {
					if(modifyUrl(url)){
						AppUtils.downloadFile(url.getDownloadUrl(), url.getStoreUrl(), parentDir);	
						downloadedFiles.add(url.getStoreUrl());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * modify the url to store in the file system
	 * 
	 * <br>
	 * Checks the already downloaded files if the file already exists then returns false
	 * 
	 * @param url
	 * @return true if file is not present already in the download file set else false
	 */
	private boolean modifyUrl(ExtractedFile url) {
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
		case OTHER:
			url.setStoreUrl(url.getStoreUrl()+"/index.html");
		default:
			break;
		}
		if(downloadedFiles.contains(url.getStoreUrl())){
			return false;
		}
		return true;
	}

}
