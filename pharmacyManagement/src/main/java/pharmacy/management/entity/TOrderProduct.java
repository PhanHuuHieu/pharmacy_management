package pharmacy.management.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

@Entity(name="ORDER_PRODUCT")
public class TOrderProduct{
	@Id
	private int id;
	private String date_time;
	private String total_money;
	private String status;
	private String fk_employee_id;
	private String fk_customer_id;
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
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public String getTotal_money() {
		return total_money;
	}
	public void setTotal_money(String total_money) {
		this.total_money = total_money;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFk_employee_id() {
		return fk_employee_id;
	}
	public void setFk_employee_id(String fk_employee_id) {
		this.fk_employee_id = fk_employee_id;
	}
	public String getFk_customer_id() {
		return fk_customer_id;
	}
	public void setFk_customer_id(String fk_customer_id) {
		this.fk_customer_id = fk_customer_id;
	}
}
