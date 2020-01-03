package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.OrderProductBean;
import pharmacy.management.bean.OrderCustomerBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.entity.TOrderProduct;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;

public interface OrderProductRepository extends BaseRepository<TOrderProduct, Integer> {
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

	void addCart(String idOrder, String id, int amount);

	void updateMoneyCart(String idOrder, String id, int amount, double price);

	void deleteCart(String id);

	List<CartBean> getListCart(String idLogin, int status);

	int checkCart(String idLogin);

	int insertOrderCart(String idLogin);

	void updateStatusOrder(String idLogin, String status);

	void updateStatusPayment(String idOrder);

	List<OrderCustomerBean> getListOrderCustomer(String idLogin);

	List<String> getListOrderProduct(String idLogin);

	List<OrderCustomerBean> getOrderDetail(String idOrder);

	void updateStatusOrderAdmin(OrderCustomerBean orderCustomerBean);
}
