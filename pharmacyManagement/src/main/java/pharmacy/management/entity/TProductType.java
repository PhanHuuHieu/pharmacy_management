package pharmacy.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="PRODUCT_TYPE")
public class TProductType {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="product_type_name")
	private String product_type_name;
	
	@Column(name="note")
	private String note;
	
	public String getProduct_type_name() {
		return product_type_name;
	}
	public void setProduct_type_name(String product_type_name) {
		this.product_type_name = product_type_name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
