package neu.edu.lab08.dao;

import java.util.ArrayList;

import neu.edu.lab08.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;



public class UserDao extends DAO {

		public User queryUserByNameAndPassword(String username, String password)
	            throws Exception {
	        try {
	            //begin();
	            Query q = getSession().createQuery("from User where username = :username and password = :password");
	            q.setString("username", username);
	            q.setString("password", password);
	            User user = (User) q.uniqueResult();
	     //       commit();
	            return user;
	        } catch (HibernateException e) {
	     //       rollback();
	            throw new Exception("Could not get user " + username, e);
	        }
	    }
		
		public User listUser(String username) throws Exception {
	        try {
//	          begin();
	        	Query q = getSession().createQuery("from User where userName = :username");
	            q.setString("username", username);
	            User user = (User) q.uniqueResult();
	     //       commit();
	            return user;
	        } catch (HibernateException e) {
	     //       rollback();
	            throw new Exception("Could not get user ", e);
	        }
	    }
		
		public void changePassword(String password, String username) throws Exception {
	        //try {
	        	//begin();
	        	Query q = getSession().createQuery("update User set UserPassword = :password where UserName = :username");
	        	q.setString("password", password);
	            q.setString("username", username);
	            q.executeUpdate();
	            //commit();
//	        } catch (HibernateException e) {
//	            //rollback();
//	            throw new Exception("Could not get user ", e);
//	            
//	        }
	    }
		
		
	
	
	
}
