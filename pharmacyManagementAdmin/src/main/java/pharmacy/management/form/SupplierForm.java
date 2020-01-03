package pharmacy.management.form;

public class SupplierForm {
	private String id;
	private String supplier_name;
	private String phone_number;
	private String supplier_address;

	public SupplierForm(String id, String phone_number, String supplier_name) {
		super();
		this.id = id;
		this.phone_number = phone_number;
		this.supplier_name = supplier_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getSupplier_address() {
		return supplier_address;
	}

	public void setSupplier_address(String supplier_address) {
		this.supplier_address = supplier_address;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
}
