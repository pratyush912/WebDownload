package com.pratz.task.model;

public class AppImage {
	
	private String downloadUrl;
	private String storeUrl;
	
	public AppImage(String downloadUrl, String storeUrl) {
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
