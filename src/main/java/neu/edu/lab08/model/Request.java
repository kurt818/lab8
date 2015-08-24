package neu.edu.lab08.model;

import java.io.Serializable;

public class Request implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer vaccineid;
	private String vaccinename;
	private String expiredate;
	private Integer availability;
	private Integer quantity;
	private Integer price;
	private Integer totalprice;
	private String status;
	private String username;
	private Vaccine vaccine;
	private User user;
	
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
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
	public Integer getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Integer totalprice) {
		this.totalprice = totalprice;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Vaccine getVaccine() {
		return vaccine;
	}
	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	public String getExpiredate() {
		return expiredate;
	}
	public void setExpiredate(String expiredate) {
		this.expiredate = expiredate;
	}
	
	
}
