package com.pratz.parser;

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
				String storeUrl = img.attr("src").substring(1);
				carrier.addImageUrl(new AppImage(imgUrl, storeUrl) );	
			}
		}
		
		//Extract all css files
		Elements linkElements = document.getElementsByTag("link");
		for(Element link : linkElements){
			String cssUrl = link.absUrl("href");
			if(cssUrl!=null){
				String storeUrl = link.attr("href").substring(1);
				carrier.addCssUrl(new AppImage(cssUrl, storeUrl));
			}
		}
		
		Elements scriptElements = document.getElementsByTag("script");
		for(Element scriptEle : scriptElements){
			String scriptUrl = scriptEle.absUrl("src");
			if(scriptUrl!=null && !scriptUrl.isEmpty()){
				String storeUrl = scriptEle.attr("src").substring(1);
				carrier.addJsUrl(new AppImage(scriptUrl, storeUrl));
			}
			
		}
		
		
		
		return carrier;
	}

}
