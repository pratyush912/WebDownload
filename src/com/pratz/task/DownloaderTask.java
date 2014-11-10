package com.pratz.task;

import java.io.File;

import com.pratz.exception.TaskException;
import com.pratz.util.UrlDownloader;



public class DownloaderTask extends AbstractTask{

	@Override
	void implement() throws TaskException {
		String url = carrier.getUrl();
		if(url==null){
			throw new TaskException("Url is null");
		}
		UrlDownloader urlDownloader = new UrlDownloader();
		File downloadedFile = urlDownloader.downloadUrl(url);
		
		carrier.setRootFile(downloadedFile);
	}

	

	
}
