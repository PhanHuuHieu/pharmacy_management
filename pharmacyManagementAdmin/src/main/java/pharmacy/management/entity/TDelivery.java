package pharmacy.management.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="DELIVERY")
public class TDelivery {
	@Id
	private int id;
	private String date_start;
	private String date_end;
	private String date_pay;
	private String date_delivery;
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
	private String status;
	private String is_return;
	private String is_delete;
	private String fk_order_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate_start() {
		return date_start;
	}
	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIs_return() {
		return is_return;
	}
	public void setIs_return(String is_return) {
		this.is_return = is_return;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public String getFk_order_id() {
		return fk_order_id;
	}
	public void setFk_order_id(String fk_order_id) {
		this.fk_order_id = fk_order_id;
	}
}
