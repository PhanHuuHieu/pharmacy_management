package pharmacy.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="SUPPLIER")
public class TSupplier {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="supplier_name")
	private String supplier_name;

	@Column(name="phone_number")
	private String phone_number;
	
	@Column(name="supplier_address")
	private String supplier_address;

	@Column(name="created_date")
	private String created_date;
	
	@Column(name="modified_date")
	private String modified_date;
	
	@Column(name="is_delete")
	private String is_delete;
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

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
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

	public String getSupplier_address() {
		return supplier_address;
	}

	public void setSupplier_address(String supplier_address) {
		this.supplier_address = supplier_address;
	}
	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
}
