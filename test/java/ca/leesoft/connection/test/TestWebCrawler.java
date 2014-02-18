package ca.leesoft.connection.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.leesoft.connection.impl.WebCrawlerImpl;

public class TestWebCrawler {
	
	final String testUrl="http://www.webtender.com/db/browse?level=2&dir=ingreds&char=%2A";
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void WebCrawlerGetContentByUrl() {
		final WebCrawlerImpl  webCrawler=new WebCrawlerImpl();
		final String content=webCrawler.getContent(testUrl);
		assertTrue(content.toUpperCase().contains("HTML"));
	}
	@Test
	public void WebCrawlerGetContent(){
		final WebCrawlerImpl  webCrawler=new WebCrawlerImpl();
		webCrawler.setUrl(testUrl);
		final String content=webCrawler.getContent();
		assertTrue(content.toUpperCase().contains("HTML"));
	}

}
