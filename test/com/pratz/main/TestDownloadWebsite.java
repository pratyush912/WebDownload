package com.pratz.main;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestDownloadWebsite {

	@Test
	public void testDownloadWebsite() throws IOException {
		DownloadWebsite.download(null, "http://www.jsoup.org/");
	}
	
	@Test
	public void testHashTagReferences() throws IOException{
		DownloadWebsite.download(null, "http://getbootstrap.com/");
	}

}
