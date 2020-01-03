package pharmacy.management.service;

import java.util.List;

import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.OrderProductBean;
import pharmacy.management.bean.OrderCustomerBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.entity.TOrderProduct;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;

public interface OrderProductService {
	List<TOrderProduct> getTotalMoney(String idLogin);

	List<OrderProductBean> getListSearchOrderProduct(OrderProductForm orderProductForm);

	List<OrderProductBean> findListReportOrderProduct(OrderReportForm orderProductForm);

	List<ReportOrderProduct> getValueOrderProduct(OrderReportForm orderProductForm);

	void getListNameGroupProduct(String month, List<ReponseBean> listData);

	void getSumAmountListNameGroupProduct(String month, List<ReponseBean> listData);

	void getListMonthDate(String year, List<ReponseBean> listData);

	void getValueFinance(String year, List<ReponseBean> listData);

	void getQuy(String month, List<ReponseBean> listData);

	void getValueQuy(String month, List<ReponseBean> listData);

	void getYear(String month, List<ReponseBean> listData);

	void getValueYear(String month, List<ReponseBean> listData);

	List<CartBean> getListCart(String idLogin, int status);

	void deleteCart(String id);

	int checkCart(String idLogin);

	void updateMoneyCart(String idOrder, String id, int amount, double price);

	void addCart(String idOrder, String id, int amount);

	int insertOrderCart(String idLogin);

	void updateStatusOrder(String idLogin, String status);

	void updateStatusPayment(String idOrder);

	List<OrderCustomerBean> getListOrderCustomer(String idLogin);

	List<String> getListOrderProduct(String idLogin);

	List<OrderCustomerBean> getOrderDetail(String idOrder);

	void updateStatusOrderAdmin(OrderCustomerBean orderCustomerBean);
}
