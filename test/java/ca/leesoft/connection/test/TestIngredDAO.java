package ca.leesoft.connection.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.leesoft.connection.dao.IngredDAO;
import ca.leesoft.connection.pojo.Ingred;

public class TestIngredDAO {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		Ingred ingred=new Ingred();
		ingred.setCode("1");
		ingred.setName("Test");
		ingred.setDescription("This is dummy description");
		ingred.setImageUrl("your_imge_url");
		IngredDAO ingredDao=new IngredDAO();
		ingredDao.saveIngred(ingred);
	}
	
	@Test
	public void testList(){
		Ingred ingred=new Ingred();
		ingred.setCode("1");
		ingred.setName("Test");
		ingred.setDescription("This is dummy description");
		ingred.setImageUrl("your_imge_url");
		IngredDAO ingredDao=new IngredDAO();
		ingredDao.saveIngred(ingred);
		
		List<Ingred> ingreds=ingredDao.list();
		assertEquals(1, ingreds.size());
		ingred=ingreds.get(0);
		assertEquals("1",ingred.getCode());
		assertEquals("Test",ingred.getName());
		assertEquals("This is dummy description",ingred.getDescription());
		assertEquals("your_imge_url",ingred.getImageUrl());
	}

}
