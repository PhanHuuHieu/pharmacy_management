package pharmacy.management.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="ORDER_PRODUCT_DETAIL")
public class TOrderProductDetail{
	@Id
	private int id;
	private int fk_order_id;
	private int fk_product_id;
	private String unit_price;
	private int amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFk_order_id() {
		return fk_order_id;
	}
	public void setFk_order_id(int fk_order_id) {
		this.fk_order_id = fk_order_id;
	}
	public int getFk_product_id() {
		return fk_product_id;
	}
	public void setFk_product_id(int fk_product_id) {
		this.fk_product_id = fk_product_id;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
