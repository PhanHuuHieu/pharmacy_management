package pharmacy.management.bean;

public class StockBean {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String type_form;
	private String date;
	private String name_stock;
	private String type;
	private String fk_product_id;
	public StockBean(String id, String type_form, String date, String fk_product_id, String status,String type,String employeeName) {
		super();
		this.id = id;
		this.type_form = type_form;
		this.date = date;
		this.fk_product_id = fk_product_id;
		this.status = status;
		this.type=type;
		this.employeeName=employeeName;
	}
	public String getFk_product_id() {
		return fk_product_id;
	}
	public void setFk_product_id(String fk_product_id) {
		this.fk_product_id = fk_product_id;
	}
	private String employeeName;
	private String status;
	private String note;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getType_form() {
		return type_form;
	}
	public void setType_form(String type_form) {
		this.type_form = type_form;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName_stock() {
		return name_stock;
	}
	public void setName_stock(String name_stock) {
		this.name_stock = name_stock;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
