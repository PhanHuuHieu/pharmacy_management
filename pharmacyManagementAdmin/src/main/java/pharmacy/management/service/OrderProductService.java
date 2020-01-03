package pharmacy.management.service;

import java.util.List;

import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.OrderCustomerBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;

public interface OrderProductService {

	List<OrderBean> getListSearchOrderProduct(OrderProductForm orderProductForm);

	List<OrderBean> findListReportOrderProduct(OrderReportForm orderProductForm);

	List<ReportOrderProduct> getValueOrderProduct(OrderReportForm orderProductForm);

	void getListNameGroupProduct(String month, List<ReponseBean> listData);

	void getSumAmountListNameGroupProduct(String month, List<ReponseBean> listData);

	void getListMonthDate(String year, List<ReponseBean> listData);

	void getValueFinance(String year, List<ReponseBean> listData);

	void getQuy(String month, List<ReponseBean> listData);

	void getValueQuy(String month, List<ReponseBean> listData);

	void getYear(String month, List<ReponseBean> listData);

	void getValueYear(String month, List<ReponseBean> listData);

	List<String> getListOrderProduct(String idLogin);

	List<OrderCustomerBean> getOrderDetail(String idOrder);

	void updateStatusOrderAdmin(OrderCustomerBean orderCustomerBean);
}
