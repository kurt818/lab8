package neu.edu.lab08.model;


public class Inventory {

	private Integer id;
	private Integer vaccineid;
	private String vaccinename;
	private String expiredate;
	private Integer quantity;
	private String user;
	private Vaccine vaccine;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Vaccine getVaccine() {
		return vaccine;
	}
	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}
	public String getExpiredate() {
		return expiredate;
	}
	public void setExpiredate(String expiredate) {
		this.expiredate = expiredate;
	}
	
	
}
