package th.co.poc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue
//	Long id;
	long customerId;
	String firstName;
	String lastName;
	String tel;
	
	
	public Customer() {
		super();
	}
	
	public Customer(long customerId, String firstName, String lastName,String tel) {
		this();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.tel = tel;
	}
	
	
	public Long getId() {
		return customerId;
	}
	public void setId(Long id) {
		this.customerId = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}

}
