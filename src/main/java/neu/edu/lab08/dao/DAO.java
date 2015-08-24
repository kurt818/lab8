package neu.edu.lab08.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;





public abstract class DAO {
	Transaction tx;
	Session session;
     
   public Session getSession(){
	   return HibernateUtil.getSessionFactory().openSession();
   }
	 
   protected DAO() {
	   
   }


   protected Transaction getNewTransaction() {
       return getSession().beginTransaction();
   }

   /*protected void commit() {
	   tx.commit();
   }*/
/*
   protected void rollback() {
       try {
           tx.rollback();
       } catch (HibernateException e) {
          
       }
       /*try {
           getSession().close();
       } catch (HibernateException e) {
          
       }*/
      
//   }

   public  void close() {
       getSession().close();
   }
	
	
}
