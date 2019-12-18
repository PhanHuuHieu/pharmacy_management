package pharmacy.management.service;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;

public interface OrderProductService {
	
	List<OrderBean> getListSearchOrderProduct(OrderProductForm orderProductForm);
	
    List<OrderBean> findListReportOrderProduct(OrderReportForm orderProductForm);
	
	List<ReportOrderProduct> getValueOrderProduct(OrderReportForm orderProductForm);
	
	void getListNameGroupProduct(String month,List<ReponseBean> listData);
	
	void getSumAmountListNameGroupProduct(String month,List<ReponseBean> listData);
	
	void getListMonthDate(String year,List<ReponseBean> listData );
	
	void getValueFinance(String year,List<ReponseBean> listData );
	
    void getQuy(String month,List<ReponseBean> listData);
	
	void getValueQuy(String month,List<ReponseBean> listData);
	
	void getYear(String month,List<ReponseBean> listData);
	
	void getValueYear(String month,List<ReponseBean> listData);
	
	List<CartBean> getListCart(String idLogin);
	
	void deleteCart(String id);
	
	int checkCart(String idLogin);
	
	void updateMoneyCart(String idOrder,String id);
	
	void addCart(String idOrder,String id,int amount);
	
	int insertOrderCart(String idLogin);
}
