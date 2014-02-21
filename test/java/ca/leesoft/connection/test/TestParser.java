package ca.leesoft.connection.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.leesoft.connection.IParser;
import ca.leesoft.connection.pojo.Ingred;
import ca.leesoft.connection.facade.IngredsList;
import ca.leesoft.connection.impl.DescriptionParserImpl;
import ca.leesoft.connection.impl.IndexPageParserImpl;

public class TestParser {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void parseIndexPage() {
		final IParser<IngredsList> parser=new IndexPageParserImpl();
		final String content="All ingredients</B><HR><UL><LI> <A HREF=\"/db/ingred/85\">151 proof rum</A><LI> <A HREF=\"/db/ingred/22\">7-Up</A>";
		IngredsList ingredsList=parser.parse(content);
		assertEquals(2,ingredsList.getList().size());
		assertEquals("151 proof rum",ingredsList.getList().get(0).getName());
		assertEquals("85",ingredsList.getList().get(0).getCode());
		assertEquals("7-Up",ingredsList.getList().get(1).getName());
		assertEquals("22",ingredsList.getList().get(1).getCode());
	}
	
	@Test
	public void hasMore(){
		final IParser<IngredsList> parser=new IndexPageParserImpl();
		String content="All ingredients</B><HR><UL><LI> <A HREF=\"/db/ingred/85\">151 proof rum</A><LI> <A HREF=\"/db/ingred/22\">7-Up</A>";
		parser.setContent(content);
		assertEquals(null,parser.hasMore());
		
		content="<LI> <A HREF=\"/db/ingred/492\">Corona</A><LI> <A HREF=\"/db/ingred/470\">Cranberries</A></UL><TABLE BORDER=0 WIDTH=\"100%\"><TR><TD><B>Back&nbsp;|&nbsp;<a target=\"_self\" href=\"browse?level=2&dir=ingreds&char=%2A&start=151\">Next</A></TD><TD ALIGN=center><small>(1 - 151)</small></TD><TD ALIGN=RIGHT>514 found.<BR></TD></TR></TABLE></BODY></HTML>";
		parser.setContent(content);
		assertEquals("browse?level=2&dir=ingreds&char=%2A&start=151",parser.hasMore());
	}
	
	@Test
	public void descriptionParser(){
		final DescriptionParserImpl parser=new DescriptionParserImpl();
		String content="<TD VALIGN=\"top\" WIDTH=\"90%\" ROWSPAN=2>"+
							"<H1>7-Up<HR></H1>"+
							"<a href=\"http://www.7up.com/\"><img src=\"/gfx/images/7up-can.gif\" width=88 height=165 alt=\"[Seven UP/7-UP]\" align=\"left\" border=0 hspace=5></a>"+
							"<P>7-UP is a lemon-lime flavored carbonated soft drink.</P>"+
							"<P>It's taste is very similar to Sprite and substituting 7-UP for Sprite or"+
							"Sprite for 7-UP in mixed drinks should work fine.</P>"+
							"<br clear=\"both\">";
		Ingred ingred=parser.parse(content);
		assertEquals("7-Up",ingred.getName());
		assertEquals("http://www.webtender.com/gfx/images/7up-can.gif",ingred.getImageUrl());
		assertEquals("7-UP is a lemon-lime flavored carbonated soft drink."
				+"It's taste is very similar to Sprite and substituting 7-UP for Sprite or"
				+"Sprite for 7-UP in mixed drinks should work fine.",ingred.getDescription());
		
	}
	public void descriptionParserWithNoPicture(){
		final DescriptionParserImpl parser=new DescriptionParserImpl();
		String content="<TD VALIGN=\"top\" WIDTH=\"90%\" ROWSPAN=2>"+
							"<H1>7-Up<HR></H1>"+
							"<P>7-UP is a lemon-lime flavored carbonated soft drink.</P>"+
							"<P>It's taste is very similar to Sprite and substituting 7-UP for Sprite or"+
							"Sprite for 7-UP in mixed drinks should work fine.</P>"+
							"<br clear=\"both\">";
		Ingred ingred=parser.parse(content);
		assertEquals("7-Up",ingred.getName());
		assertNull(ingred.getImageUrl());
		assertEquals("7-UP is a lemon-lime flavored carbonated soft drink."
				+"It's taste is very similar to Sprite and substituting 7-UP for Sprite or"
				+"Sprite for 7-UP in mixed drinks should work fine.",ingred.getDescription());
	}

}
