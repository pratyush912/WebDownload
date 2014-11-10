package com.pratz.util;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.pratz.parser.HtmlParser;
import com.pratz.task.model.AppImage;
import com.pratz.task.model.Carrier;

public class TestAppUtils {
	
	@Test
	public void testDownloadFile() throws IOException{
		Document doc = Jsoup.connect("http://google.com").get();
		Carrier carrier = HtmlParser.parseHTML(doc);
		List<AppImage> imageUrls = carrier.getImageUrls();
		for(AppImage img : imageUrls){
			File file = AppUtils.downloadFile(img.getDownloadUrl(), img.getStoreUrl().substring(1));
			assertTrue(file.exists());
		}
	}

}
