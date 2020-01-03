package pharmacy.management.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="CUSTOMER")
public class TCustomer {
	@Id
	private int id;
	private String phone_number;
	private String name;
	private String email;
	private String password;
	private String created_date;
	private String modified_date;
	private String number_account;
	private String picture;
	private String created_by;
	private String modified_by;
	private String id_social;
	private String is_block;
	private String is_delete;
	private String barcode;
	private int point;
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getIs_block() {
		return is_block;
	}
	public void setIs_block(String is_block) {
		this.is_block = is_block;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public String getId_social() {
		return id_social;
	}
	public void setId_social(String id_social) {
		this.id_social = id_social;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	public String getNumber_account() {
		return number_account;
	}
	public void setNumber_account(String number_account) {
		this.number_account = number_account;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
}
