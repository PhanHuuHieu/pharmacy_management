package pharmacy.management.bean;

public class OrderProductBean extends OrderBean {
	private String nameEmployee;
	private double totalMoney;
	private String nameProduct;
	private double amount;
	private double priceSell;
	private double priceOrginal;
	private double tax;

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPriceSell() {
		return priceSell;
	}

	public void setPriceSell(double priceSell) {
		this.priceSell = priceSell;
	}

	public double getPriceOrginal() {
		return priceOrginal;
	}

	public void setPriceOrginal(double priceOrginal) {
		this.priceOrginal = priceOrginal;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public OrderProductBean(String id, String nameCustomer, String nameEmployee, double totalMoney, String date) {
		super(id, date, nameCustomer);
		this.nameEmployee = nameEmployee;
		this.totalMoney = totalMoney;
	}

	public OrderProductBean(double tax, String nameProduct, String nameEmployee, String nameProductGroup) {
		super(nameProductGroup);
		this.tax = tax;
		this.nameProduct = nameProduct;
		this.nameEmployee = nameEmployee;
	}

	public String getNameEmployee() {
		return nameEmployee;
	}

	public void setNameEmployee(String nameEmployee) {
		this.nameEmployee = nameEmployee;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

}
