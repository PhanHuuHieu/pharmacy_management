package pharmacy.management.bean;

public class OrderBean {
	private int id;
	private String nameCustomer;
	private String nameEmployee;
	private double totalMoney;
	private String date;
	private String nameProductGroup;
	private String nameProduct;
	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getNameProductGroup() {
		return nameProductGroup;
	}

	public void setNameProductGroup(String nameProductGroup) {
		this.nameProductGroup = nameProductGroup;
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
	private double amount;
	private double priceSell;
	private double priceOrginal;
	private double tax;
	
	public OrderBean()
	{
		
	}
	
	public OrderBean(int id, String nameCustomer, String nameEmployee, double totalMoney, String date) {
		super();
		this.id = id;
		this.nameCustomer = nameCustomer;
		this.nameEmployee = nameEmployee;
		this.totalMoney = totalMoney;
		this.date = date;
	}

	public OrderBean(double tax,String nameProduct,String nameEmployee,String nameProductGroup) {
		super();
		this.tax = tax;
		this.nameProduct=nameProduct;
		this.nameEmployee=nameEmployee;
		this.nameProductGroup=nameProductGroup;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameCustomer() {
		return nameCustomer;
	}
	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
