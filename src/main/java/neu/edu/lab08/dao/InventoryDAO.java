/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package neu.edu.lab08.dao;

import java.sql.Date;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import neu.edu.lab08.model.Inventory;
import neu.edu.lab08.model.Request;
import neu.edu.lab08.model.User;
import neu.edu.lab08.model.Vaccine;

public class InventoryDAO extends DAO{
    
	public InventoryDAO(){
	    	super();
	    }
	
    public ArrayList<Inventory> listInventoryByUser(String username) throws Exception {
    	ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
        try {
        	Query q = getSession().createQuery("from Inventory where user = :username");
        	q.setString("username", username);
        	inventoryList = (ArrayList<Inventory>) q.list();

            //commit();
            return inventoryList;
        } catch (HibernateException e) {
     //       rollback();
            throw new Exception("Could not get user ", e);
        }
    }
    
    public Inventory listInventoryById(String inventoryId) throws Exception {
        try {
        	Query q = getSession().createQuery("from Inventory where id = :id");
        	q.setInteger("id", Integer.parseInt(inventoryId));
        	Inventory inventory = (Inventory) q.uniqueResult();

            //commit();
            return inventory;
        } catch (HibernateException e) {
     //       rollback();
            throw new Exception("Could not get user ", e);
        }
    }
    
    public void updateInventory(Integer newquantity, String inventoryId) throws Exception {
    	Transaction tx = null;
    	try {
    		Session session = getSession();    
    		tx = session.beginTransaction();
        	Query q = session.createQuery("update Inventory set quantity = :quantity where id = :id");
	            q.setInteger("quantity", newquantity);
	            q.setInteger("id", Integer.parseInt(inventoryId));
	            
	            q.executeUpdate();
	            //getSession().flush();
	            tx.commit();
	            session.close();
        } catch (HibernateException e) {
            tx.rollback();
            session.close();
            throw new Exception("Could not get user ", e);
        }
    }
    

}
