package ca.leesoft.connection.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.leesoft.connection.IParser;
import ca.leesoft.connection.pojo.Ingred;

public class DescriptionParserImpl implements IParser<Ingred> {
	String content;
	Ingred ingred;
	final static String URL_PREFIX="http://www.webtender.com/gfx/images/";
	public Ingred parse(String content) {
		// TODO Auto-generated method stub
		this.content=content;
		if(ingred==null)
		{
			ingred=new Ingred();
		}
		Pattern p=Pattern.compile("<H1>(.*?)<HR></H1>");//name
		Matcher matcher=p.matcher(content);
		if(matcher.find()){
			ingred.setName(matcher.group(1));
		}
		p=Pattern.compile("<img src=\"/gfx/images/(.*)\\.(gif|jpg|png)\"");
		matcher=p.matcher(content);
		if(matcher.find())
		{
			final String img_url=URL_PREFIX+matcher.group(1)+"."+matcher.group(2);
			ingred.setImageUrl(img_url);
		}
		
		p=Pattern.compile("<P>(.*?)</P>");
		
		String description="";
		matcher=p.matcher(content);
		while(matcher.find())
		{
			description+=matcher.group(1);
		}
		if(!"".equals(description))
		{
			ingred.setDescription(description);
		}	
		return ingred;
	}

	public String hasMore() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setContent(String content) {
		// TODO Auto-generated method stub
		this.content=content;
	}
	public void setIngred(Ingred ingred)
	{
		this.ingred=ingred;
	}

}
