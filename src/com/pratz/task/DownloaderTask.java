package com.pratz.task;

import com.pratz.exception.TaskException;



public class DownloaderTask extends AbstractTask{

	@Override
	void implement() throws TaskException {
		String url = carrier.getUrl();
		if(url==null){
			throw new TaskException("Url is null");
		}
//		UrlDownloader urlDownloader = new UrlDownloader();
//		File downloadedFile = urlDownloader.downloadUrl(url);
//		
//		carrier.setRootFile(downloadedFile);
	}

	

	
}
