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

import neu.edu.lab08.model.InsuredPatient;
import neu.edu.lab08.model.Patient;
import neu.edu.lab08.model.UninsuredPatient;
import neu.edu.lab08.model.User;

public class PatientDAO extends DAO{
    
	public PatientDAO(){
	    	super();
	    }
	
    public ArrayList<InsuredPatient> listInsuredPatientByUsername(String username) throws Exception {
    	ArrayList<InsuredPatient> insuredPatientList = new ArrayList<InsuredPatient>();
        try {
        	Query q = getSession().createQuery("from InsuredPatient where hospital = :username and status = :status");
            q.setString("username", username);
            q.setInteger("status", 1);
            insuredPatientList = (ArrayList<InsuredPatient>) q.list();

            //commit();
            return insuredPatientList;
        } catch (HibernateException e) {
     //       rollback();
            throw new Exception("Could not get user ", e);
        }
    }
    
    public ArrayList<UninsuredPatient> listUninsuredPatientByUsername(String username) throws Exception {
    	ArrayList<UninsuredPatient> uninsuredPatientList = new ArrayList<UninsuredPatient>();
        try {
        	Query q = getSession().createQuery("from UninsuredPatient where hospital = :username and status = :status");
            q.setString("username", username);
            q.setInteger("status", 1);
            uninsuredPatientList = (ArrayList<UninsuredPatient>) q.list();

            //commit();
            return uninsuredPatientList;
        } catch (HibernateException e) {
     //       rollback();
            throw new Exception("Could not get user ", e);
        }
    }
    
    public void deleteinsuredpatient(String insuredpatientid) throws Exception{
    	Transaction tx = null;
    	try {
    			Session session = getSession();    
    			tx = session.beginTransaction();
  	            Query q = session.createQuery("delete InsuredPatient where patientid = :patientid");
  	            q.setString("patientid", insuredpatientid);
  	            q.executeUpdate();
  	            tx.commit();
	            session.close();
  	        } catch (HibernateException e) {
  	        	tx.rollback();
  	            session.close();
  	            throw new Exception("Could not get user ", e);
  	        }
    }
    
    public void deleteuninsuredpatient(String uninsuredpatientid) throws Exception{
    	Transaction tx = null;
    	try {
    			Session session = getSession();    
    			tx = session.beginTransaction();
  	            Query q = session.createQuery("delete UninsuredPatient where patientid = :patientid");
  	            q.setString("patientid", uninsuredpatientid);
  	            q.executeUpdate();
  	            tx.commit();
	            session.close();
  	        } catch (HibernateException e) {
  	        	tx.rollback();
  	            session.close();
  	            throw new Exception("Could not get user ", e);
  	        }
    }
    
    public void updateInsuredpatient(Integer insuredamount, Integer patientid) throws Exception{
    	Transaction tx = null;
    	try {
    			Session session = getSession();    
    			tx = session.beginTransaction();
  	            Query q = session.createQuery("update InsuredPatient set insuredamount = :insuredamount where patientid = :patientid");
  	            q.setInteger("insuredamount", insuredamount);
  	            q.setInteger("patientid", patientid);
  	            q.executeUpdate();
  	            tx.commit();
	            session.close();
  	        } catch (HibernateException e) {
  	        	tx.rollback();
  	            session.close();
  	            throw new Exception("Could not get user ", e);
  	        }
    }
    
    public void updateUninsuredpatient(Integer account, Integer patientid) throws Exception{
    	Transaction tx = null;
    	try {
    			Session session = getSession();    
    			tx = session.beginTransaction();
  	            Query q = session.createQuery("update UninsuredPatient set account = :account where patientid = :patientid");
  	            q.setInteger("account", account);
	            q.setInteger("patientid", patientid);
  	            q.executeUpdate();
  	            tx.commit();
	            session.close();
  	        } catch (HibernateException e) {
  	        	tx.rollback();
  	            session.close();
  	            throw new Exception("Could not get user ", e);
  	        }
    }
    
    public Patient patientById(Integer patientId) throws Exception {
        try {
        	Query q = getSession().createQuery("from Patient where id = :id");
            q.setInteger("id", patientId);
            Patient patient = (Patient) q.uniqueResult();

            //commit();
            return patient;
        } catch (HibernateException e) {
     //       rollback();
            throw new Exception("Could not get user ", e);
        }
    }
    
    public InsuredPatient insuredpatientById(Integer patientId) throws Exception {
        try {
        	Query q = getSession().createQuery("from InsuredPatient where patientid = :id");
            q.setInteger("id", patientId);
            InsuredPatient insuredpatient = (InsuredPatient) q.uniqueResult();

            //commit();
            return insuredpatient;
        } catch (HibernateException e) {
     //       rollback();
            throw new Exception("Could not get user ", e);
        }
    }
    public UninsuredPatient uninsuredpatientById(Integer patientId) throws Exception {
        try {
        	Query q = getSession().createQuery("from UninsuredPatient where patientid = :id");
            q.setInteger("id", patientId);
            UninsuredPatient uninsuredpatient = (UninsuredPatient) q.uniqueResult();

            //commit();
            return uninsuredpatient;
        } catch (HibernateException e) {
     //       rollback();
            throw new Exception("Could not get user ", e);
        }
    }
    
    public void updateInsuredpatientStatus(String patientid) throws Exception{
    	Transaction tx = null;
    	try {
    			Session session = getSession();    
    			tx = session.beginTransaction();
  	            Query q = session.createQuery("update InsuredPatient set status = :status where patientid = :patientid");
  	            q.setInteger("status", 2);
  	            q.setInteger("patientid", Integer.parseInt(patientid));
  	            q.executeUpdate();
  	            tx.commit();
	            session.close();
  	        } catch (HibernateException e) {
  	        	tx.rollback();
  	            session.close();
  	            throw new Exception("Could not get user ", e);
  	        }
    }
    
    public void updateUninsuredpatientStatus(String patientid) throws Exception{
    	Transaction tx = null;
    	try {
    			Session session = getSession();    
    			tx = session.beginTransaction();
  	            Query q = session.createQuery("update UninsuredPatient set status = :status where patientid = :patientid");
  	            q.setInteger("status", 2);
	            q.setInteger("patientid", Integer.parseInt(patientid));
  	            q.executeUpdate();
  	            tx.commit();
	            session.close();
  	        } catch (HibernateException e) {
  	        	tx.rollback();
  	            session.close();
  	            throw new Exception("Could not get user ", e);
  	        }
    }
    

}
