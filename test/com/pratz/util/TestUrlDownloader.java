package com.pratz.util;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

public class TestUrlDownloader {

	@Test
	public void test() throws IOException {
		UrlDownloader urlDownload = new UrlDownloader();
		Document doc = urlDownload.downloadUrl("http://google.com");
		Assert.assertNotNull(doc);
	}

}
