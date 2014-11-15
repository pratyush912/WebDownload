package com.pratz.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pratz.task.model.ExtractedFile;
import com.pratz.task.model.Carrier;

/**
 * This class is used for parsing the html file and extracting
 * js,css,images and linked urls present in the html
 *
 */
public class HtmlParser {
	
	/**
	 * Parser the given document and extracts js css iamges and urls
	 * 
	 * @param document
	 * @return
	 */
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
					carrier.addExtUrl(new ExtractedFile(imgUrl, storeUrl));
				} catch (MalformedURLException e) {
					String baseUri = img.baseUri();
					if(!baseUri.endsWith("/")) baseUri = baseUri + "/";
					storeUrl = imgUrl.replace(baseUri.substring(0,nthIndexOf(baseUri, '/', 3)), "");
					if(storeUrl.length()>1 && storeUrl.charAt(1)=='.'){
						storeUrl = storeUrl.replaceAll("(\\.\\./)+", "");
					}
					imgUrl = imgUrl.replaceAll("(\\.\\./)+", "");
					carrier.addImageUrl(new ExtractedFile(imgUrl, storeUrl));
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
					carrier.addExtUrl(new ExtractedFile(cssUrl, storeUrl));
				} catch (MalformedURLException e) {
					String baseUri = link.baseUri();
					if(!baseUri.endsWith("/")) baseUri = baseUri + "/";
					storeUrl = cssUrl.replace(baseUri.substring(0,nthIndexOf(baseUri, '/', 3)), "");
					if(storeUrl.length()>1 && storeUrl.charAt(1)=='.'){
						storeUrl = storeUrl.replaceAll("(\\.\\./)+", "");
					}
					cssUrl = cssUrl.replaceAll("(\\.\\./)+", "");
					carrier.addCssUrl(new ExtractedFile(cssUrl, storeUrl));
				}
				
			}
		}
		
		//Extract all script files
		Elements scriptElements = document.getElementsByTag("script");
		for(Element scriptEle : scriptElements){
			String scriptUrl = scriptEle.absUrl("src");
			if(scriptUrl!=null && !scriptUrl.isEmpty()){
				String storeUrl = scriptEle.attr("src");
				try {
					new URL(storeUrl);
					carrier.addExtUrl(new ExtractedFile(scriptUrl, storeUrl));
				} catch (MalformedURLException e) {
					String baseUri = scriptEle.baseUri();
					if(!baseUri.endsWith("/")) baseUri = baseUri + "/";
					storeUrl = scriptUrl.replace(baseUri.substring(0,nthIndexOf(baseUri, '/', 3)), "");
					if(storeUrl.length()>1 && storeUrl.charAt(1)=='.'){
						storeUrl = storeUrl.replaceAll("(\\.\\./)+", "");
					}
					scriptUrl = scriptUrl.replaceAll("(\\.\\./)+", "");
					carrier.addJsUrl(new ExtractedFile(scriptUrl, storeUrl));
				}
			}
			
		}
		
		//Extract all links
		Elements anchorElements = document.getElementsByTag("a");
		for(Element anchorEle : anchorElements){
			String anchorUrl = anchorEle.absUrl("href").split("#|\\?")[0];
			if(anchorUrl!=null && !anchorUrl.isEmpty()){
				String[] splittedHref = anchorEle.attr("href").split("#|\\?");
				if(splittedHref.length>0){
					String storeUrl = splittedHref[0];
					try {
						new URL(storeUrl);
						carrier.addExtUrl(new ExtractedFile(anchorUrl, storeUrl));
					} catch (MalformedURLException e) {
						String baseUri = anchorEle.baseUri();
						if(!baseUri.endsWith("/")) baseUri = baseUri + "/";
						storeUrl = anchorUrl.replace(baseUri.substring(0,nthIndexOf(baseUri, '/', 3)), "");
						if(storeUrl.length()>1 && storeUrl.charAt(1)=='.'){
							storeUrl = storeUrl.replaceAll("(\\.\\./)+", "");
						}
						anchorUrl = anchorUrl.replaceAll("(\\.\\./)+", "");
						carrier.addOtherUrl(new ExtractedFile(anchorUrl, storeUrl));
					}
				}
			}
			
		}
		return carrier;
	}
	
	/**
	 * For getting the nth index of a character
	 */
	private static int nthIndexOf(String text, char exChar, int n){
	    for (int i = 0; i < text.length(); i++){
	        if (text.charAt(i) == exChar){
	            n--;
	            if (n == 0){
	                return i;
	            }
	        }
	    }
	    return -1;
	}
	
}
