package pharmacy.management.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="DEPT")
public class TDept {
	@Id
	private int id;
	private String date_start;
	private String date_end;
	private double money_own;
	private double money_paided;
	private double limit;
	private String term;
	private int fk_supplier_id;
	private String note;
	private int status;
	private String over_limit;
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
	public double getMoney_own() {
		return money_own;
	}
	public void setMoney_own(double money_own) {
		this.money_own = money_own;
	}
	public double getMoney_paided() {
		return money_paided;
	}
	public void setMoney_paided(double money_paided) {
		this.money_paided = money_paided;
	}
	public double getLimit() {
		return limit;
	}
	public void setLimit(double limit) {
		this.limit = limit;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public int getFk_supplier_id() {
		return fk_supplier_id;
	}
	public void setFk_supplier_id(int fk_supplier_id) {
		this.fk_supplier_id = fk_supplier_id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOver_limit() {
		return over_limit;
	}
	public void setOver_limit(String over_limit) {
		this.over_limit = over_limit;
	}
}
