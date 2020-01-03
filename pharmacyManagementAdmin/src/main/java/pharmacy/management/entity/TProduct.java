package pharmacy.management.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="PRODUCT")
public class TProduct{
	@Id
	private int id;
	private String name;
	private String weight;
	private String height;
	private String color;
	private String date_manufature;
	private String date_expirate;
	private String picture;
	private String picture1;
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
	private String picture2;
	private String price_sell;
	private double price_orginal;
	private int fk_supplier_id;
	private int fk_product_group_id;
	private int fk_unit_id;
	private String note;
	private double tax;
	private String created_date;
	private String modified_date;
	private String status;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TProduct(int id,String name,String note, String price_sell,String picture,String description,String weight,String color,String picture1,String picture2) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.price_sell = price_sell;
		this.note = note;
		this.description = description;
		this.weight = weight;
		this.color = color;
		this.picture1 = picture1;
		this.picture2 = picture2;
	}
	public TProduct(int id,String name,String note, String price_sell,String picture,String description,String weight,String color) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.price_sell = price_sell;
		this.note = note;
		this.description = description;
		this.weight = weight;
		this.color = color;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TProduct(int id,String name, String color,String price)
	{
		this.id = id;
		this.name = name;
		this.color = color;
		this.price_sell = price;
	}
	public TProduct()
	{
		
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
	public String getPrice_sell() {
		return price_sell;
	}
	public void setPrice_sell(String price_sell) {
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
