package neu.edu.lab08.model;

import java.io.Serializable;


public class Patient implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String gender;
	private String dob;
	private String patienttype;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPatienttype() {
		return patienttype;
	}
	public void setPatienttype(String patienttype) {
		this.patienttype = patienttype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
