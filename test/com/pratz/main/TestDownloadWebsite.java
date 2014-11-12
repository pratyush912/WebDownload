package com.pratz.main;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestDownloadWebsite {

	@Test
	public void testDownloadWebsite() throws IOException {
		DownloadWebsite downWeb = new DownloadWebsite(null, "http://getbootstrap.com");
	}

}
