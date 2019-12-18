package pharmacy.management.bean;

public class CartBean {
	private int id;
	private int amount;
	private int idPro;
	private String name;
	private String price_sell;
	private String note;
	private String color;
	private String weight;
	private String picture;
	private String idOrder;
	private String total_money;
	public String getTotal_money() {
		return total_money;
	}
	public void setTotal_money(String total_money) {
		this.total_money = total_money;
	}
	public String getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public CartBean(int id, int amount, int idPro, String name, String price_sell, String note, String color,
			String weight,String picture,String idOrder,String total_money) {
		super();
		this.id = id;
		this.amount = amount;
		this.idPro = idPro;
		this.name = name;
		this.price_sell = price_sell;
		this.note = note;
		this.color = color;
		this.weight = weight;
		this.picture = picture;
		this.idOrder = idOrder;
		this.total_money = total_money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getIdPro() {
		return idPro;
	}
	public void setIdPro(int idPro) {
		this.idPro = idPro;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice_sell() {
		return price_sell;
	}
	public void setPrice_sell(String price_sell) {
		this.price_sell = price_sell;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
}
