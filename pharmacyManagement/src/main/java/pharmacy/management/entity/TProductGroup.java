package pharmacy.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="PRODUCT_GROUP")
public class TProductGroup {
	@Id
	@Column(name="id")
	private int id;
	@Column(name="product_group_name")
	private String product_group_name;
	@Column(name="note")
	private String note;
	@Column(name="top")
	private String top;
	@Column(name="picture")
	private String picture;
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getProduct_group_name() {
		return product_group_name;
	}
	public void setProduct_group_name(String product_group_name) {
		this.product_group_name = product_group_name;
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
	public TProductGroup(int id, String product_group_name, String note, String top, String picture) {
		super();
		this.id = id;
		this.product_group_name = product_group_name;
		this.note = note;
		this.top = top;
		this.picture = picture;
	}
	public TProductGroup()
	{
		
	}
}
