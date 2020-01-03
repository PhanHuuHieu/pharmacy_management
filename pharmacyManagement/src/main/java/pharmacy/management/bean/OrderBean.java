package pharmacy.management.bean;

public class OrderBean {
	private String id;
	private String nameCustomer;
	private String date;
	private String nameProducts;

	public OrderBean(String id, String date) {
		super();
		this.id = id;
		this.date = date;
	}

	public OrderBean(String id, String date, String nameCustomer) {
		super();
		this.id = id;
		this.date = date;
		this.nameCustomer = nameCustomer;
	}

	public OrderBean(String nameProducts) {
		super();
		this.nameProducts = nameProducts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNameProducts() {
		return nameProducts;
	}

	public void setNameProducts(String nameProducts) {
		this.nameProducts = nameProducts;
	}
}
