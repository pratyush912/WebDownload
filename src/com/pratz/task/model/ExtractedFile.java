package com.pratz.task.model;

/**
 * Model file contains info about the extracted file from the html
 *
 */
public class ExtractedFile {
	
	private String downloadUrl;
	private String storeUrl;
	
	public ExtractedFile(String downloadUrl, String storeUrl) {
		this.setDownloadUrl(downloadUrl);
		this.setStoreUrl(storeUrl);
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getStoreUrl() {
		return storeUrl;
	}

	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}
}
