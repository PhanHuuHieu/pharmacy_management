package pharmacy.management.form;

public class OrderProductForm {
	private int idCustomer;
	private int idEmployee;
	private String dateOrder;

	public OrderProductForm(int idCustomer, int idEmployee, String dateOrder) {
		super();
		this.idCustomer = idCustomer;
		this.idEmployee = idEmployee;
		this.dateOrder = dateOrder;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(String dateOrder) {
		this.dateOrder = dateOrder;
	}

}
