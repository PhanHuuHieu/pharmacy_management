package pharmacy.management.bean;

public class DeptBean {
	private int id;
	private String date_start;
	private String date_end;
	private double money_own;
	private double money_paided;
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
	public int getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(int supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getOver_limit() {
		return over_limit;
	}
	public void setOver_limit(String over_limit) {
		this.over_limit = over_limit;
	}
	private double limit;
	private int supplier_name;
	private String over_limit;

}
