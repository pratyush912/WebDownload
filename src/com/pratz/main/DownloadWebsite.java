package com.pratz.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;

import com.pratz.parser.HtmlParser;
import com.pratz.task.model.AppImage;
import com.pratz.task.model.Carrier;
import com.pratz.util.AppUtils;
import com.pratz.util.UrlDownloader;

public class DownloadWebsite {
	
	public static void main(String[] args) {
		try {
			DownloadWebsite downWeb = new DownloadWebsite(null, "http://jsoup.org");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public DownloadWebsite(File parentDir, String url) throws IOException {
		UrlDownloader urlDownload = new UrlDownloader();
		Document document = urlDownload.downloadUrl(url);
		
		if(parentDir==null){
			parentDir = AppUtils.createUniqueDir();
		}
		
		urlDownload.writeToFile(parentDir);
		
		Carrier carrier = HtmlParser.parseHTML(document);
		
		List<AppImage> cssUrls = carrier.getCssUrls();
		List<AppImage> jsUrls = carrier.getJsUrls();
		List<AppImage> imgUrls = carrier.getImageUrls();
		List<AppImage> otherUrls = carrier.getOtherUrls();
		
		downloadFiles(cssUrls,parentDir);
		downloadFiles(jsUrls,parentDir);
		downloadFiles(imgUrls,parentDir);
		downloadFiles(otherUrls,parentDir);
		
	}

	private void downloadFiles(List<AppImage> fileUrls, File parentDir) {
		if(!fileUrls.isEmpty()){
			for(AppImage url : fileUrls){
				try {
					if(!"/".equals(url.getStoreUrl()))
						AppUtils.downloadFile(url.getDownloadUrl(), url.getStoreUrl(), parentDir);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
