package pharmacy.management.service;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.bean.StockBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TStock;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.form.StockExportForm;
import pharmacy.management.form.StockForm;

public interface StockService {
	
	void getNameProductFromStock(String stock_name,List<ReponseBean> listData);
	
	void getValueProductFromStock(String stock_name,List<ReponseBean> listData);
	
	List<TStock> getListStock(TStock stockForm);
	
	List<StockBean> getStock(String id);
	
	void deleteStock(String id);
	
void insertStock(TStock stockBean);
	
	void updateStock(TStock stockBean);
	
	long countIdStock(int id);
	
	List<StockBean> getListExport(StockExportForm stockExportForm);
}
