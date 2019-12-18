package pharmacy.management.form;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
	private int id;
	private String name;
	private String weight;
	private String height;
	private String color;
	private String date_manufature;
	private String date_expirate;
	private MultipartFile[] picture;
	private double price_sell;
	private double price_orginal;
	private int fk_supplier_id;
	private int fk_product_type_id;
	private int fk_product_group_id;
	private int fk_unit_id;
	private String note;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDate_manufature() {
		return date_manufature;
	}
	public void setDate_manufature(String date_manufature) {
		this.date_manufature = date_manufature;
	}
	public String getDate_expirate() {
		return date_expirate;
	}
	public void setDate_expirate(String date_expirate) {
		this.date_expirate = date_expirate;
	}
	public double getPrice_sell() {
		return price_sell;
	}
	public void setPrice_sell(double price_sell) {
		this.price_sell = price_sell;
	}
	public double getPrice_orginal() {
		return price_orginal;
	}
	public void setPrice_orginal(double price_orginal) {
		this.price_orginal = price_orginal;
	}
	public int getFk_supplier_id() {
		return fk_supplier_id;
	}
	public void setFk_supplier_id(int fk_supplier_id) {
		this.fk_supplier_id = fk_supplier_id;
	}
	public int getFk_product_type_id() {
		return fk_product_type_id;
	}
	public void setFk_product_type_id(int fk_product_type_id) {
		this.fk_product_type_id = fk_product_type_id;
	}
	public int getFk_product_group_id() {
		return fk_product_group_id;
	}
	public void setFk_product_group_id(int fk_product_group_id) {
		this.fk_product_group_id = fk_product_group_id;
	}
	public int getFk_unit_id() {
		return fk_unit_id;
	}
	public void setFk_unit_id(int fk_unit_id) {
		this.fk_unit_id = fk_unit_id;
	}
	public MultipartFile[] getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile[] picture) {
		this.picture = picture;
	}

}
