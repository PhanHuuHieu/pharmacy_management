package pharmacy.management.bean;

import java.util.List;

public class ChartBean {
	private List<ReponseBean> listChartColumn;
	private List<ReponseBean> listChartCircle;
	private List<ReponseBean> listChartLine;

	public List<ReponseBean> getListChartColumn() {
		return listChartColumn;
	}

	public void setListChartColumn(List<ReponseBean> listChartColumn) {
		this.listChartColumn = listChartColumn;
	}

	public List<ReponseBean> getListChartCircle() {
		return listChartCircle;
	}

	public void setListChartCircle(List<ReponseBean> listChartCircle) {
		this.listChartCircle = listChartCircle;
	}

	public List<ReponseBean> getListChartLine() {
		return listChartLine;
	}

	public void setListChartLine(List<ReponseBean> listChartLine) {
		this.listChartLine = listChartLine;
	}
}
