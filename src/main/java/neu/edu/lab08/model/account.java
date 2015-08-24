package neu.edu.lab08.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "account")
public class account {

	@Id @GeneratedValue
	   @Column(name = "id")
	   private int id;
	
	@Column(name = "name")
	   private String name;
	
	@Column(name = "amount")
	   private int amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	} 
	
	
}
