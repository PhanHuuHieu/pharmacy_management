package pharmacy.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.OrderProductBean;
import pharmacy.management.bean.OrderCustomerBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.entity.TOrderProduct;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.respository.OrderProductRepository;
import pharmacy.management.service.OrderProductService;

@Service
public class OrderProductServiceImpl implements OrderProductService {

	@Autowired
	private OrderProductRepository orderproductRepository;

	@Override
	public List<OrderProductBean> getListSearchOrderProduct(OrderProductForm orderProductForm) {
		return orderproductRepository.getListSearchOrderProduct(orderProductForm);
	}

	@Override
	public List<OrderProductBean> findListReportOrderProduct(OrderReportForm orderProductForm) {
		return orderproductRepository.findListReportOrderProduct(orderProductForm);
	}

	@Override
	public List<ReportOrderProduct> getValueOrderProduct(OrderReportForm orderProductForm) {
		return orderproductRepository.getValueOrderProduct(orderProductForm);
	}

	@Override
	public void getListNameGroupProduct(String month, List<ReponseBean> listData) {
		orderproductRepository.getListNameGroupProduct(month, listData);
	}

	@Override
	public void getSumAmountListNameGroupProduct(String month, List<ReponseBean> listData) {
		orderproductRepository.getSumAmountListNameGroupProduct(month, listData);
	}

	@Override
	public void getListMonthDate(String year, List<ReponseBean> listData) {
		orderproductRepository.getListMonthDate(year, listData);
	}

	@Override
	public void getValueFinance(String year, List<ReponseBean> listData) {
		orderproductRepository.getValueFinance(year, listData);
	}

	@Override
	public void getQuy(String month, List<ReponseBean> listData) {
		orderproductRepository.getQuy(month, listData);
	}

	@Override
	public void getValueQuy(String month, List<ReponseBean> listData) {
		orderproductRepository.getValueQuy(month, listData);
	}

	@Override
	public void getYear(String month, List<ReponseBean> listData) {
		orderproductRepository.getYear(month, listData);
	}

	@Override
	public void getValueYear(String month, List<ReponseBean> listData) {
		orderproductRepository.getValueYear(month, listData);
	}

	@Override
	public List<CartBean> getListCart(String idLogin, int status) {
		return orderproductRepository.getListCart(idLogin, status);
	}

	@Override
	public void deleteCart(String id) {
		orderproductRepository.deleteCart(id);
	}

	@Override
	public int checkCart(String idLogin) {
		return orderproductRepository.checkCart(idLogin);
	}

	@Override
	public void updateMoneyCart(String idOrder, String id, int amount, double price) {
		orderproductRepository.updateMoneyCart(idOrder, id, amount, price);
	}

	@Override
	public void addCart(String idOrder, String id, int amount) {
		orderproductRepository.addCart(idOrder, id, amount);
	}

	@Override
	public int insertOrderCart(String idLogin) {
		return orderproductRepository.insertOrderCart(idLogin);
	}

	@Override
	public void updateStatusOrder(String idLogin, String status) {
		orderproductRepository.updateStatusOrder(idLogin, status);
	}

	@Override
	public List<TOrderProduct> getTotalMoney(String idLogin) {
		return orderproductRepository.getTotalMoney(idLogin);
	}

	@Override
	public void updateStatusPayment(String idOrder) {
		orderproductRepository.updateStatusPayment(idOrder);
	}

	@Override
	public List<OrderCustomerBean> getListOrderCustomer(String idLogin) {
		return orderproductRepository.getListOrderCustomer(idLogin);
	}

	@Override
	public List<String> getListOrderProduct(String idLogin) {
		return orderproductRepository.getListOrderProduct(idLogin);
	}

	@Override
	public List<OrderCustomerBean> getOrderDetail(String idOrder) {
		return orderproductRepository.getOrderDetail(idOrder);
	}

	@Override
	public void updateStatusOrderAdmin(OrderCustomerBean orderCustomerBean) {
		orderproductRepository.updateStatusOrderAdmin(orderCustomerBean);
	}

}
