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

import neu.edu.lab08.model.UsedVaccine;
import neu.edu.lab08.model.User;
import neu.edu.lab08.model.Vaccine;

public class VaccineDAO extends DAO{
    
	public VaccineDAO(){
	    	super();
	    }
	
    public ArrayList<Vaccine> listVaccine() throws Exception {
    	ArrayList<Vaccine> vaccineList = new ArrayList<Vaccine>();
        try {
        	//begin();
        	Query q = getSession().createQuery("from Vaccine where status = :status");
        	q.setInteger("status", 1);
        	vaccineList = (ArrayList<Vaccine>) q.list();

            //commit();
            return vaccineList;
        } catch (HibernateException e) {
     //       rollback();
            throw new Exception("Could not get user ", e);
        }
    }
    
    public ArrayList<Vaccine> listVaccineByUsername(String username) throws Exception {
  	ArrayList<Vaccine> vaccineList = new ArrayList<Vaccine>();
      try {
    	  //begin();
      	Query q = getSession().createQuery("from Vaccine where manufacture = :username and status = :status");
          q.setString("username", username);
          q.setInteger("status", 1);
          vaccineList = (ArrayList<Vaccine>) q.list();

          // commit();
          return vaccineList;
      } catch (HibernateException e) {
   //       rollback();
          throw new Exception("Could not get user ", e);
      }
  }
    
    public Vaccine listVaccineByVaccineid(Integer vaccineid) throws Exception {
          try {
        	  //begin();
          	Query q = getSession().createQuery("from Vaccine where id = :id");
              q.setInteger("id", vaccineid);
              Vaccine vaccine = (Vaccine) q.uniqueResult();

              // commit();
              return vaccine;
          } catch (HibernateException e) {
       //       rollback();
              throw new Exception("Could not get user ", e);
          }
      }
    
    public ArrayList<UsedVaccine> listUsedVaccineByUsername(String username) throws Exception {
      	ArrayList<UsedVaccine> usedvaccineList = new ArrayList<UsedVaccine>();
          try {
        	  //begin();
          	Query q = getSession().createQuery("from UsedVaccine where hospital = :username");
              q.setString("username", username);
              usedvaccineList = (ArrayList<UsedVaccine>) q.list();

              // commit();
              return usedvaccineList;
          } catch (HibernateException e) {
       //       rollback();
              throw new Exception("Could not get user ", e);
          }
      }
    
    public void deleteVaccine(String vaccineId) throws Exception{
    	Transaction tx = null;
    	try {
  	        Session session = getSession();    
    		tx = session.beginTransaction();
  	            Query q = session.createQuery("delete Vaccine where id = :id");
  	            q.setInteger("id", Integer.parseInt(vaccineId));
  	            q.executeUpdate();
  	            tx.commit();
  	            session.close();
  	            
  	        } catch (HibernateException e) {
  	        	System.out.println(e);
  	            tx.rollback();
  	            session.close();
  	            throw new Exception("Could not get user ", e);
  	        }
    }
    
    public void updateVaccine(Integer vaccineId, Integer newAvailability) throws Exception{
    	Transaction tx = null;
    	try {
  	            tx = getNewTransaction();
  	            Query q = getSession().createQuery("update Vaccine set availability = :newAvailability where id = :id");
  	            q.setInteger("id", vaccineId);
  	            q.setInteger("newAvailability", newAvailability);
  	            q.executeUpdate();
  	            tx.commit();
  	        } catch (HibernateException e) {
  	            tx.rollback();
  	            throw new Exception("Could not get user ", e);
  	        }
    }
    
    public void updateVaccineStatus(String vaccineId, Integer status) throws Exception{
    	Transaction tx = null;
    	try {
    		Session session = getSession();    
    		tx = session.beginTransaction();
  	            Query q = session.createQuery("update Vaccine set status = :status where id = :id");
  	            q.setInteger("id", Integer.parseInt(vaccineId));
  	            q.setInteger("status", status);
  	            q.executeUpdate();
  	            tx.commit();
  	          session.close();
  	        } catch (HibernateException e) {
  	            tx.rollback();
  	            throw new Exception("Could not get user ", e);
  	        }
    }
    

}
