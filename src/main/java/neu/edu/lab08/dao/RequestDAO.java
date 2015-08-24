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

import neu.edu.lab08.model.Request;
import neu.edu.lab08.model.User;
import neu.edu.lab08.model.Vaccine;

public class RequestDAO extends DAO{
    
	public RequestDAO(){
	    	super();
	    }
	
    public ArrayList<Request> listRequest() throws Exception {
    	ArrayList<Request> requestList = new ArrayList<Request>();
        try {
        	Query q = getSession().createQuery("from Request");
        	requestList = (ArrayList<Request>) q.list();

            //commit();
            return requestList;
        } catch (HibernateException e) {
     //       rollback();
            throw new Exception("Could not get user ", e);
        }
    }
    
    public Request requestByRequestid(String requestid) throws Exception {
        try {
        	Query q = getSession().createQuery("from Request where id = :id");
        	q.setInteger("id", Integer.parseInt(requestid));
        	Request request = (Request)q.uniqueResult();

            //commit();
            return request;
        } catch (HibernateException e) {
     //       rollback();
            throw new Exception("Could not get user ", e);
        }
    }
    
    public void updateRequest(String status, Integer vaccineid) throws Exception {
    	Transaction tx = null;
    	try {
    		Session session = getSession();    
    		tx = session.beginTransaction();
        	Query q = session.createQuery("update Request set status = :status where vaccineid = :vaccineid");
	            q.setString("status", status);
	            q.setInteger("vaccineid", vaccineid);
	            q.executeUpdate();
	            tx.commit();
	            session.close();
        } catch (HibernateException e) {
            tx.rollback();
            session.close();
            throw new Exception("Could not get user ", e);
        }
    }
    public void updateRequestAvailability(Integer newavailability, Integer vaccineid) throws Exception {
        Transaction tx = null;
    	try {
    		Session session = getSession();    
    		tx = session.beginTransaction();
        	Query q = session.createQuery("update Request set availability = :availability where vaccineid = :vaccineid");
	            q.setInteger("availability", newavailability);
	            q.setInteger("vaccineid", vaccineid);
	            
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
    public void updateVaccineAvailability(Integer newavailability, Integer vaccineid) throws Exception {
    	Transaction tx = null;
    	try {
    		Session session = getSession();    
    		tx = session.beginTransaction();
        	Query q = session.createQuery("update Vaccine set availability = :availability where id = :vaccineid");
	            q.setInteger("availability", newavailability);
	            q.setInteger("vaccineid", vaccineid);
	            
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
