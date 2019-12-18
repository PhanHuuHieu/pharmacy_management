package pharmacy.management.bean;

import java.math.BigDecimal;

public class ReponseBean {
	
	private BigDecimal y;
	private String label;
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ReponseBean()
	{
		
	}
	
	public ReponseBean(String lable) {
		super();
		this.label = lable;
	}

	public BigDecimal getY() {
		return y;
	}
	public void setY(BigDecimal y) {
		this.y = y;
	}
	
}
