package com.pratz.task.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Carrier {
	
	private String url;
	private File rootFile;
	
	private String baseUrl;
	
	private List<AppImage> imageUrls = new ArrayList<>();
	private List<AppImage> jsUrls = new ArrayList<>();
	private List<AppImage> cssUrls = new ArrayList<>();
	private List<AppImage> otherUrls = new ArrayList<>();
	private List<AppImage> extUrls = new ArrayList<>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public File getRootFile() {
		return rootFile;
	}

	public void setRootFile(File rootFile) {
		this.rootFile = rootFile;
	}

	public List<AppImage> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<AppImage> imageUrls) {
		this.imageUrls = imageUrls;
	}
	
	public void addImageUrl(AppImage imageUrl){
		this.imageUrls.add(imageUrl);
	}

	public List<AppImage> getJsUrls() {
		return jsUrls;
	}

	public void setJsUrls(List<AppImage> jsUrls) {
		this.jsUrls = jsUrls;
	}
	
	public void addJsUrl(AppImage jsUrl){
		this.jsUrls.add(jsUrl);
	}

	public List<AppImage> getCssUrls() {
		return cssUrls;
	}

	public void setCssUrls(List<AppImage> cssUrls) {
		this.cssUrls = cssUrls;
	}
	
	public void addCssUrl(AppImage cssUrl){
		this.cssUrls.add(cssUrl);
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<AppImage> getOtherUrls() {
		return otherUrls;
	}

	public void setOtherUrls(List<AppImage> otherUrls) {
		this.otherUrls = otherUrls;
	}
	
	public void addOtherUrl(AppImage otherUrl){
		this.otherUrls.add(otherUrl);
	}

	public List<AppImage> getExtUrls() {
		return extUrls;
	}

	public void setExtUrls(List<AppImage> extUrls) {
		this.extUrls = extUrls;
	}
	
	public void addExtUrl(AppImage extUrl){
		this.extUrls.add(extUrl);
	}
	
}
