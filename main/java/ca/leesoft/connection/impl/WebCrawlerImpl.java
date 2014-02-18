package ca.leesoft.connection.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import ca.leesoft.connection.IWebCrawler;

public class WebCrawlerImpl implements IWebCrawler {
	
	String url;
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		final URL urlObject;
		BufferedReader br=null;
		final HttpURLConnection conn;
		
		try {
			urlObject = new URL(url);
			conn=(HttpURLConnection)urlObject.openConnection();
			conn.setRequestMethod("GET");  

		    conn.setRequestProperty(  
		                        "User-Agent",  
		                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");  
		    conn.setRequestProperty("Accept", "text/html");
			br=new BufferedReader(
						new InputStreamReader(conn.getInputStream())
					);
			final StringBuilder  sb=new StringBuilder();
			String inputLine;
			while((inputLine=br.readLine())!=null)
			{
				sb.append(inputLine);
			}
			return sb.toString();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				if(br!=null)
				{
					br.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
		
		
	}

	@Override
	public String getContent(String url) {
		// TODO Auto-generated method stub
		this.url=url;
		return getContent();
	}

}
