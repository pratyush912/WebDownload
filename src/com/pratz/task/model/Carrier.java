package com.pratz.task.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Model object that contains extracted information from the html
 *
 */
public class Carrier {
	
	private String url;
	private File rootFile;
	
	private String baseUrl;
	
	private List<ExtractedFile> imageUrls = new ArrayList<>();
	private List<ExtractedFile> jsUrls = new ArrayList<>();
	private List<ExtractedFile> cssUrls = new ArrayList<>();
	private List<ExtractedFile> otherUrls = new ArrayList<>();
	private List<ExtractedFile> extUrls = new ArrayList<>();

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

	public List<ExtractedFile> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<ExtractedFile> imageUrls) {
		this.imageUrls = imageUrls;
	}
	
	public void addImageUrl(ExtractedFile imageUrl){
		this.imageUrls.add(imageUrl);
	}

	public List<ExtractedFile> getJsUrls() {
		return jsUrls;
	}

	public void setJsUrls(List<ExtractedFile> jsUrls) {
		this.jsUrls = jsUrls;
	}
	
	public void addJsUrl(ExtractedFile jsUrl){
		this.jsUrls.add(jsUrl);
	}

	public List<ExtractedFile> getCssUrls() {
		return cssUrls;
	}

	public void setCssUrls(List<ExtractedFile> cssUrls) {
		this.cssUrls = cssUrls;
	}
	
	public void addCssUrl(ExtractedFile cssUrl){
		this.cssUrls.add(cssUrl);
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<ExtractedFile> getOtherUrls() {
		return otherUrls;
	}

	public void setOtherUrls(List<ExtractedFile> otherUrls) {
		this.otherUrls = otherUrls;
	}
	
	public void addOtherUrl(ExtractedFile otherUrl){
		this.otherUrls.add(otherUrl);
	}

	public List<ExtractedFile> getExtUrls() {
		return extUrls;
	}

	public void setExtUrls(List<ExtractedFile> extUrls) {
		this.extUrls = extUrls;
	}
	
	public void addExtUrl(ExtractedFile extUrl){
		this.extUrls.add(extUrl);
	}
	
}
