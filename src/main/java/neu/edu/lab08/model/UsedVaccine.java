package neu.edu.lab08.model;

import java.io.Serializable;



public class UsedVaccine implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer vaccineid;
	private String vaccinename;
	private Integer patientid;
	private Integer vaccineprice;
	private String date;
	private Integer payamount;
	private String hospital;
	private Patient patient;
	private Vaccine vaccine;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVaccineid() {
		return vaccineid;
	}
	public void setVaccineid(Integer vaccineid) {
		this.vaccineid = vaccineid;
	}
	public String getVaccinename() {
		return vaccinename;
	}
	public void setVaccinename(String vaccinename) {
		this.vaccinename = vaccinename;
	}
	public Integer getPatientid() {
		return patientid;
	}
	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getPayamount() {
		return payamount;
	}
	public void setPayamount(Integer payamount) {
		this.payamount = payamount;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Vaccine getVaccine() {
		return vaccine;
	}
	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}
	public Integer getVaccineprice() {
		return vaccineprice;
	}
	public void setVaccineprice(Integer vaccineprice) {
		this.vaccineprice = vaccineprice;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	
}
