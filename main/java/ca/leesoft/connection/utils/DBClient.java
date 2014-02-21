package ca.leesoft.connection.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;



public class DBClient {
	private static DBClient instance;
	static{
		instance=new DBClient();
	}
	
	private DBConfig config;
	
	public DBClient getDBClient(){
		return instance;
	}
	
	public void setConfig(DBConfig config)
	{
		this.config=config;
	}
	
	public <T> List<T> query(String sql,ResultSetHandler<List<T>> h,Object... parameters){
		Connection conn=null;
		try {
			Class.forName(config.getDriverName()).newInstance();
			conn=DriverManager.getConnection(config.getConnectionString());
			QueryRunner run = new QueryRunner();
			List<T> result = run.query(
			        conn, "SELECT * FROM Person WHERE name=?", h,parameters);
			return result;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	
	public int update(String sql, Object... params)
	{
		Connection conn=null;
		QueryRunner run = new QueryRunner();
		try {
			Class.forName(config.getDriverName()).newInstance();
			conn=DriverManager.getConnection(config.getConnectionString());
			int ret=run.update(sql, params);
			return ret;
		}  catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return -1;
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
		
	}
	
}
