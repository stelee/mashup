package ca.leesoft.connection.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ca.leesoft.connection.pojo.Ingred;
import ca.leesoft.connection.utils.HiberateUtil;

public class IngredDAO {
	public long saveIngred(Ingred ingred)
	{
		Session session=HiberateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			id=(Long) session.save(ingred);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}finally
		{
			session.close();
		}
		return id;
	}
	@SuppressWarnings("unchecked")
	public List<Ingred> list()
	{
		Session session=HiberateUtil.getSessionFactory().openSession();
		Transaction transaction;
		transaction=session.beginTransaction();
		List<Ingred> ingreds=session.createQuery("from ingred").list();
		transaction.commit();
		return ingreds;
	}
}
