package com.pratz.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.pratz.parser.TestHtmlParser;
import com.pratz.util.TestAppUtils;
import com.pratz.util.TestUrlDownloader;

@RunWith(Suite.class)
@SuiteClasses({ TestDownloadWebsite.class, TestAppUtils.class, TestHtmlParser.class, TestUrlDownloader.class})
public class ApplitcationTestSuite {
}
