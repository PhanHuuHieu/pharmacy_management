package pharmacy.management.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="LOG")
public class TLog {
	@Id
	private int id;
	private String date_time;
	private String content;
	private int fk_employee_id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFk_employee_id() {
		return fk_employee_id;
	}
	public void setFk_employee_id(int fk_employee_id) {
		this.fk_employee_id = fk_employee_id;
	}
	
}
