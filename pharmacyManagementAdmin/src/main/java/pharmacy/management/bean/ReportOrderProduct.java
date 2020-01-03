package pharmacy.management.bean;

public class ReportOrderProduct {

	private double sumAmountProduct;
	private double sumPriceSellProduct;
	private double sumPriceOrginal;
	private double sumTotalMoney;
	private int stt;
	private String nameProduct;
	private String date;
	private double tax;

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public ReportOrderProduct(double sumAmountProduct, double sumPriceSellProduct, double sumPriceOrginal,
			double sumTotalMoney) {
		super();
		this.sumAmountProduct = sumAmountProduct;
		this.sumPriceSellProduct = sumPriceSellProduct;
		this.sumPriceOrginal = sumPriceOrginal;
		this.sumTotalMoney = sumTotalMoney;
	}

	public double getSumAmountProduct() {
		return sumAmountProduct;
	}

	public void setSumAmountProduct(double sumAmountProduct) {
		this.sumAmountProduct = sumAmountProduct;
	}

	public double getSumPriceSellProduct() {
		return sumPriceSellProduct;
	}

	public void setSumPriceSellProduct(double sumPriceSellProduct) {
		this.sumPriceSellProduct = sumPriceSellProduct;
	}

	public double getSumPriceOrginal() {
		return sumPriceOrginal;
	}

	public void setSumPriceOrginal(double sumPriceOrginal) {
		this.sumPriceOrginal = sumPriceOrginal;
	}

	public double getSumTotalMoney() {
		return sumTotalMoney;
	}

	public void setSumTotalMoney(double sumTotalMoney) {
		this.sumTotalMoney = sumTotalMoney;
	}

}
