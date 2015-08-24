package neu.edu.lab08.model;

import java.io.Serializable;



public class Vaccine implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer price;
	private Integer availability;
	private String manufacture;
	private String producedate;
	private String expiredate;
	private Integer status;
	private Inventory inventory;
	private Request request;
	private UsedVaccine usedvaccine;
	
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getProducedate() {
		return producedate;
	}
	public void setProducedate(String producedate) {
		this.producedate = producedate;
	}
	public String getExpiredate() {
		return expiredate;
	}
	public void setExpiredate(String expiredate) {
		this.expiredate = expiredate;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public UsedVaccine getUsedvaccine() {
		return usedvaccine;
	}
	public void setUsedvaccine(UsedVaccine usedvaccine) {
		this.usedvaccine = usedvaccine;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
