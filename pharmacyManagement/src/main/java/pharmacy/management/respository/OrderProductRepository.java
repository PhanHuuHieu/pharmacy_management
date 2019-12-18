package pharmacy.management.respository;

import java.util.HashMap;
import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.entity.TOrderProduct;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;

public interface OrderProductRepository extends BaseRepository<TOrderProduct, Integer> {
	
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
	
	void addCart(String idOrder,String id,int amount);
	
	void updateMoneyCart(String idOrder,String id);
	
	void deleteCart(String id);
	
	List<CartBean> getListCart(String idLogin);
	
	int checkCart(String idLogin);
	
	int insertOrderCart(String idLogin);
}
