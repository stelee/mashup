package ca.leesoft.connection.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.leesoft.connection.IParser;
import ca.leesoft.connection.pojo.Ingred;
import ca.leesoft.connection.facade.IngredsList;

public class IndexPageParserImpl implements IParser<IngredsList> {
	private String content=null;
	public IngredsList parse(String content) {
		this.setContent(content);
		final Pattern p=Pattern.compile("<LI>\\s<A HREF=\"/db/ingred/\\d+\">(.*?)</A>");
		final Pattern p2=Pattern.compile("\"/db/ingred/(\\d{1,5})\"");
		final Matcher matcher=p.matcher(content);
		final IngredsList ingredsList=new IngredsList();
		while(matcher.find()){
			final String codeStringForParsing=matcher.group(0);
			
			final Matcher matcher2=p2.matcher(codeStringForParsing);
			if(matcher2.find()){
				//there is the code
				Ingred ingred=new Ingred();
				ingred.setCode(matcher2.group(1));
				ingred.setName(matcher.group(1));
				ingredsList.getList().add(ingred);
			}
		}
		
		
		
		return ingredsList;
	}

	public String hasMore() {
		final Pattern p=Pattern.compile("<a target=\"_self\" href=\"(.*?)\">Next</A>");
		final Matcher matcher=p.matcher(this.content);
		if(matcher.find()){
			return matcher.group(1);
		}else
		{
			return null;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
