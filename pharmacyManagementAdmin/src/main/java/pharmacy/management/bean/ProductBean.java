package pharmacy.management.bean;

public class ProductBean {
	private int id;
	private String name;
	private double price_orginal;
	private double price_sell;
	private String weight;
	private String color;
	private String date_manufature;
	private String date_expirate;
	private String product_group_name;
	private String picture;
	private String supplier_name;
	private String note;
	private String unit_name;

	public String getProduct_group_name() {
		return product_group_name;
	}

	public void setProduct_group_name(String product_group_name) {
		this.product_group_name = product_group_name;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
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

	public double getPrice_orginal() {
		return price_orginal;
	}

	public void setPrice_orginal(double price_orginal) {
		this.price_orginal = price_orginal;
	}

	public double getPrice_sell() {
		return price_sell;
	}

	public void setPrice_sell(double price_sell) {
		this.price_sell = price_sell;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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
}
