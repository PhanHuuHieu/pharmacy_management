package pharmacy.management.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

@Entity(name="STOCK")
public class TStock{
	@Id
	private int id;
	private String type_form;
	private String date;
	private String stock_name;
	private String type;
	private String status;
	private int fk_employee_id;
	private int fk_product_id;
	public int getFk_product_id() {
		return fk_product_id;
	}
	public void setFk_product_id(int fk_product_id) {
		this.fk_product_id = fk_product_id;
	}
	private String note;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	private String is_delete;
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getStock_name() {
		return stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFk_employee_id() {
		return fk_employee_id;
	}
	public void setFk_employee_id(int fk_employee_id) {
		this.fk_employee_id = fk_employee_id;
	}
}
