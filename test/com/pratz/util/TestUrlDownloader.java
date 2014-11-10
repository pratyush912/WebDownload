package com.pratz.util;

import org.junit.Test;

public class TestUrlDownloader {

	@Test
	public void test() {
		UrlDownloader urlDownload = new UrlDownloader();
		urlDownload.downloadUrl("http://google.com");
	}

}
