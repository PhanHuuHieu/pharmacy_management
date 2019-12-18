package pharmacy.management.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="ADDRESS")
public class TAddress {
	@Id
	private int id;
	private String name;
	private String phone_number;
	private String address_detail;
	private int fk_customer_id;
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
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public int getFk_customer_id() {
		return fk_customer_id;
	}
	public void setFk_customer_id(int fk_customer_id) {
		this.fk_customer_id = fk_customer_id;
	}
}
