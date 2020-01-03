package pharmacy.management.bean;

public class PurchaseBean {
	private String name;
	private String phone_number;
	private String address_detail;
	private String status_pay;
	private String total_money;

	public String getTotal_money() {
		return total_money;
	}

	public void setTotal_money(String total_money) {
		this.total_money = total_money;
	}

	public PurchaseBean() {

	}

	public PurchaseBean(String name, String phone_number, String address_detail) {
		super();
		this.name = name;
		this.phone_number = phone_number;
		this.address_detail = address_detail;
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

	public String getStatus_pay() {
		return status_pay;
	}

	public void setStatus_pay(String status_pay) {
		this.status_pay = status_pay;
	}
}
