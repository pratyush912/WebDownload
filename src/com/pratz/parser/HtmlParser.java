package com.pratz.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pratz.task.model.AppImage;
import com.pratz.task.model.Carrier;

public class HtmlParser {
	
	public static Carrier parseHTML(Document document){
		if(document==null){
			throw new IllegalArgumentException("document object can not be null");
		}
		Carrier carrier = new Carrier();
		
		//extract all image urls from the page
		Elements imgLst = document.getElementsByTag("img");
		for(Element img : imgLst){
			String imgUrl = img.absUrl("src");
			if(imgUrl!=null){
				String storeUrl = img.attr("src");
				try {
					new URL(storeUrl);
					carrier.addExtUrl(new AppImage(imgUrl, storeUrl));
				} catch (MalformedURLException e) {
					carrier.addImageUrl(new AppImage(imgUrl, storeUrl));
				}
			}
		}
		
		//Extract all css files
		Elements linkElements = document.getElementsByTag("link");
		for(Element link : linkElements){
			String cssUrl = link.absUrl("href");
			if(cssUrl!=null){
				String storeUrl = link.attr("href");
				try {
					new URL(storeUrl);
					carrier.addExtUrl(new AppImage(cssUrl, storeUrl));
				} catch (MalformedURLException e) {
					carrier.addCssUrl(new AppImage(cssUrl, storeUrl));
				}
				
			}
		}
		
		Elements scriptElements = document.getElementsByTag("script");
		for(Element scriptEle : scriptElements){
			String scriptUrl = scriptEle.absUrl("src");
			if(scriptUrl!=null && !scriptUrl.isEmpty()){
				String storeUrl = scriptEle.attr("src");
				try {
					new URL(storeUrl);
					carrier.addExtUrl(new AppImage(scriptUrl, storeUrl));
				} catch (MalformedURLException e) {
					carrier.addJsUrl(new AppImage(scriptUrl, storeUrl));
				}
			}
			
		}
		
		Elements anchorElements = document.getElementsByTag("a");
		for(Element anchorEle : anchorElements){
			String anchorUrl = anchorEle.absUrl("href");
			if(anchorUrl!=null && !anchorUrl.isEmpty()){
				String storeUrl = anchorEle.attr("href");
				try {
					new URL(storeUrl);
					carrier.addExtUrl(new AppImage(anchorUrl, storeUrl));
				} catch (MalformedURLException e) {
					carrier.addOtherUrl(new AppImage(anchorUrl, storeUrl));
				}
			}
			
		}
		return carrier;
	}

}
