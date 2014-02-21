package ca.leesoft.connection.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDerby {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testDBClient(){
		fail("Not implemented yet");
		
	}
	
	@Test
	public void testDerby() {
		final String driver="org.apache.derby.jdbc.EmbeddedDriver";
		try {
			Class.forName(driver).newInstance();
			Connection conn=DriverManager.getConnection("jdbc:derby:memory:derbyDB;create=true");
			String sql="create table TTEST (code int primary key)";
			Statement stmt=conn.createStatement();
			int ret =stmt.executeUpdate(sql);
			assertEquals(0,ret);
			
			stmt.executeUpdate("insert into TTEST (code) values(1)");
			stmt.executeUpdate("insert into TTEST (code) values(2)");
			stmt.executeUpdate("insert into TTEST (code) values(3)");
			stmt.executeUpdate("insert into TTEST (code) values(4)");
			ResultSet rs=stmt.executeQuery("select count(*) as resultNumber from TTEST");
			if(rs.next())
			{
				assertEquals(4,rs.getInt("resultNumber"));
			}else
			{
				fail("No result in the database");
			}
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("InstantiationException");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("IllegalAccessException");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("ClassNotFoundException");
		}catch(SQLException e){
			e.printStackTrace();
			fail("SQLException");
		}
	}

}
