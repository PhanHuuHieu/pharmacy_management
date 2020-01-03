package pharmacy.management.bean;

public class OrderCustomerBean {
	private String id;
	private String nameProducts;

	public String getNameProducts() {
		return nameProducts;
	}

	public void setNameProducts(String nameProducts) {
		this.nameProducts = nameProducts;
	}

	private String date_start;
	private String date_end;
	private String date_pay;
	private String date_delivery;
	private String status;
	private String nameCustomer;
	private String payment;
	private String address;

	public OrderCustomerBean(String id, String nameCustomer, String address, String payment, String status,
			String date_start, String date_pay, String date_delivery, String date_end) {
		super();
		this.id = id;
		this.date_start = date_start;
		this.date_end = date_end;
		this.date_pay = date_pay;
		this.date_delivery = date_delivery;
		this.status = status;
		this.nameCustomer = nameCustomer;
		this.payment = payment;
		this.address = address;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}

	public String getDate_pay() {
		return date_pay;
	}

	public void setDate_pay(String date_pay) {
		this.date_pay = date_pay;
	}

	public String getDate_delivery() {
		return date_delivery;
	}

	public void setDate_delivery(String date_delivery) {
		this.date_delivery = date_delivery;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public OrderCustomerBean(String id, String date_start) {
		super();
		this.id = id;
		this.date_start = date_start;
	}

	public OrderCustomerBean(String id, String date_start, String nameProducts) {
		super();
		this.id = id;
		this.date_start = date_start;
		this.nameProducts = nameProducts;
	}

	public OrderCustomerBean() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate_start() {
		return date_start;
	}

	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}
}
