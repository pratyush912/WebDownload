package com.pratz.main;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestDownloadWebsite {

	@Test
	public void testDownloadWebsite() throws IOException {
		DownloadWebsite webDwn = new DownloadWebsite("http://www.jsoup.org/");
		webDwn.download();
	}
	
	@Test
	public void testHashTagReferences() throws IOException{
		DownloadWebsite webDwn = new DownloadWebsite("http://getbootstrap.com/");
		webDwn.download();
	}
	
	@Test
	public void testNestedHtml() throws IOException{
		DownloadWebsite webDwn = new DownloadWebsite("http://nvie.com/posts/a-successful-git-branching-model/");
		webDwn.download();
	}

}
