package com.pratz.util;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pratz.constant.AppConstants;
import com.pratz.parser.HtmlParser;
import com.pratz.task.model.Carrier;
import com.pratz.task.model.ExtractedFile;

public class TestAppUtils {
	
	private static File parent;
	
	@BeforeClass
	public static void setUp(){
		parent = new File(AppConstants.DOWNLOAD_DIRECTORY);
		if(!parent.exists()){
			parent.mkdirs();
		}
	}
	
	@Test
	public void testDownloadFile() throws IOException{
		Document doc = Jsoup.connect("http://google.com").get();
		Carrier carrier = HtmlParser.parseHTML(doc);
		List<ExtractedFile> imageUrls = carrier.getImageUrls();
		for(ExtractedFile img : imageUrls){
			File file = AppUtils.downloadFile(img.getDownloadUrl(), img.getStoreUrl(), parent);
			assertTrue(file.exists());
		}
	}
	
	@Test
	public void testCreateUniqueDir() throws IOException{
		File uniqueDir = AppUtils.createUniqueDir();
		assertTrue(uniqueDir.exists() && uniqueDir.isDirectory());
	}

}
