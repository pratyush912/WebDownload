package com.pratz.main;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;

import com.pratz.parser.HtmlParser;
import com.pratz.task.model.AppImage;
import com.pratz.task.model.Carrier;
import com.pratz.util.AppUtils;
import com.pratz.util.SourceIdentifier;
import com.pratz.util.UrlDownloader;

public class DownloadWebsite {
	
	private Set<String> downloadedFiles;
	private File parentDir;
	private String url;
	private String filePath;

	
	public DownloadWebsite(Set<String> downloadedFiles, File parentDir, String url, String filePath) {
		if(downloadedFiles==null){
			downloadedFiles = new HashSet<>();
		}
		this.downloadedFiles = downloadedFiles;
		this.parentDir = parentDir;
		this.url = url;
		this.filePath = filePath;
	}
	
	public DownloadWebsite(String url){
		this(null,null,url,null);
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
/*	public static File download(File parentDir, String url) throws IOException {
		return download(parentDir, url,null);
	}
*/	
	public File download() throws IOException {
		UrlDownloader urlDownload = new UrlDownloader();
		Document document = urlDownload.downloadUrl(url);
		
		if(document!=null){
		
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
			downloadFileRecursively(otherUrls,parentDir);

		}
		
		return parentDir;
	}
	
	private void downloadFileRecursively(List<AppImage> fileUrls, File parentDir) {
		if(!fileUrls.isEmpty()){
			for(AppImage url : fileUrls){
				try {
					
					if(modifyUrl(url)){
						//					AppUtils.downloadFile(url.getDownloadUrl(), url.getStoreUrl(), parentDir);
						File downloadFile = new File(parentDir.getAbsolutePath()+url.getStoreUrl());
						if(downloadFile.exists()){
							continue;
						}
						DownloadWebsite downWeb = new DownloadWebsite(downloadedFiles, parentDir, url.getDownloadUrl(), url.getStoreUrl());
						downWeb.download();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void downloadFiles(List<AppImage> fileUrls, File parentDir) {
		if(!fileUrls.isEmpty()){
			for(AppImage url : fileUrls){
				try {
					if(modifyUrl(url)){
						AppUtils.downloadFile(url.getDownloadUrl(), url.getStoreUrl(), parentDir);	
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private boolean modifyUrl(AppImage url) {
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
		if(downloadedFiles.contains(url.getStoreUrl())){
			return false;
		}
		return true;
	}

}
