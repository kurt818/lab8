package neu.edu.lab08.model;

public class InsuredPatient extends Patient{

	private String hospital;
	private Integer insuredamount;
	private Integer status;
	private String picture;

	public Integer getInsuredamount() {
		return insuredamount;
	}

	public void setInsuredamount(Integer insuredamount) {
		this.insuredamount = insuredamount;
	}
	
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
