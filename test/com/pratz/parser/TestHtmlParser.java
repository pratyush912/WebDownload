package com.pratz.parser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.pratz.task.model.Carrier;

public class TestHtmlParser {
	
	@Test
	public void testImageHtmlParser() throws IOException {
		Document doc = Jsoup.connect("http://google.com").get();
		Carrier carrier = HtmlParser.parseHTML(doc);
		System.out.println(carrier.getImageUrls().size());
		assertFalse(carrier.getImageUrls().isEmpty());
	}
	
	@Test
	public void testCssHtmlParser() throws IOException{
		Document doc = Jsoup.connect("http://jsoup.org").get();
		Carrier carrier = HtmlParser.parseHTML(doc);
		System.out.println(carrier.getImageUrls().size());
		assertFalse(carrier.getCssUrls().isEmpty());
	}
	
	@Test
	public void testJsHtmlParser() throws IOException{
		Document doc = Jsoup.connect("http://jsoup.org").get();
		Carrier carrier = HtmlParser.parseHTML(doc);
		System.out.println(carrier.getImageUrls().size());
		assertFalse(carrier.getJsUrls().isEmpty());
	}

}
