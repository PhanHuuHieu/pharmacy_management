package pharmacy.management.respository.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.bean.CartBean;
import pharmacy.management.bean.OrderProductBean;
import pharmacy.management.bean.OrderCustomerBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.common.CommonContanst;
import pharmacy.management.entity.TOrderProduct;
import pharmacy.management.entity.TOrderProductDetail;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.OrderProductForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.respository.OrderProductRepository;
import pharmacy.management.respository.ProductRepository;

@Repository
public class OrderProductRepositoryImpl extends BaseRepositoryImpl<TOrderProduct, Integer>
		implements OrderProductRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<OrderProductBean> getListSearchOrderProduct(OrderProductForm orderProductForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT  DISTINCT opro.id, cus.name x,emp.name, opro.total_money, convert(varchar,opro.date_time,23),opro.status ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("LEFT JOIN CUSTOMER cus ON cus.id = opro.fk_customer_id ");
		sql.append("LEFT JOIN EMPLOYEE emp ON emp.id = opro.fk_employee_id ");
		sql.append("WHERE opro.status = 1 AND opro.is_delete = '0' ");
		if (orderProductForm.getIdCustomer() != -1) {
			sql.append("AND cus.id = " + orderProductForm.getIdCustomer());
		}
		if (orderProductForm.getIdEmployee() != -1) {
			sql.append("AND emp.id = " + orderProductForm.getIdEmployee());
		}
		if (!orderProductForm.getDateOrder().equals("")) {
			sql.append("AND opro.date_time <= '" + orderProductForm.getDateOrder() + "'");
		}
		Query query = entityManager.createNativeQuery(sql.toString());
		List<OrderProductBean> listOrderProduct = query.getResultList();
//		List<Object[]> listOrderProductObject=query.getResultList();
//		List<OrderBean> listOrderProduct = new ArrayList<OrderBean>();
//		for(Object[] obj : listOrderProductObject)
//		{
//			String id = String.valueOf(obj[0]); 
//			String nameCustomer = String.valueOf(obj[1]);
//			String nameEmployee = String.valueOf(obj[2]);
//			String totalMoney = String.valueOf(obj[3]);
//			String date = String.valueOf(obj[4]);
//			OrderBean orderBean = new OrderBean(Integer.parseInt(id),nameCustomer,nameEmployee,Double.valueOf(totalMoney),date);
//			listOrderProduct.add(orderBean);
//		}
		return listOrderProduct;
	}

	@Override
	public List<OrderProductBean> findListReportOrderProduct(OrderReportForm orderProductForm) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT pr.tax, pr.name a, emp.name b, prg.product_group_name ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("LEFT JOIN CUSTOMER cus ON cus.id = opro.fk_customer_id ");
		sql.append("LEFT JOIN EMPLOYEE emp ON emp.id = opro.fk_employee_id ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL oprod ON oprod.fk_order_id = opro.id ");
		sql.append("LEFT JOIN PRODUCT pr ON pr.id = oprod.fk_product_id ");
		sql.append("LEFT JOIN PRODUCT_GROUP prg ON prg.id = pr.fk_product_group_id ");
		sql.append("WHERE opro.status = 1 AND opro.is_delete = '0' ");
		if (!orderProductForm.getGroupProduct().equals("-1")) {
			sql.append("AND prg.id = " + orderProductForm.getGroupProduct() + " ");
		}
		if (!orderProductForm.getNameEmployee().equals("-1")) {
			sql.append("AND emp.id = " + orderProductForm.getNameEmployee() + " ");
		}
		if (!orderProductForm.getDate().equals("")) {
			sql.append("AND DATEDIFF(year, opro.date_time,'" + orderProductForm.getDate() + "') = 0 ");
			sql.append("AND DATEDIFF(month, opro.date_time,'" + orderProductForm.getDate() + "') = 0 ");
			sql.append("AND DATEDIFF(day, opro.date_time,'" + orderProductForm.getDate() + "') = 0 ");
		}

		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listReportOrderProductObject = query.getResultList();
		List<OrderProductBean> listReportOrderProduct = new ArrayList<OrderProductBean>();
		int i = 0;
		for (Object[] obj : listReportOrderProductObject) {
			if (listReportOrderProductObject.size() > 1) {
				if (i != 0) {
					OrderProductBean orderBean = new OrderProductBean(Double.valueOf(String.valueOf(obj[0])),
							String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3]));
					listReportOrderProduct.add(orderBean);
				}
			} else {
				OrderProductBean orderBean = new OrderProductBean(Double.valueOf(String.valueOf(obj[0])),
						String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3]));
				listReportOrderProduct.add(orderBean);
			}
			i++;
		}
		return listReportOrderProduct;
	}

	@Override
	public List<ReportOrderProduct> getValueOrderProduct(OrderReportForm orderProductForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT SUM(oprod.amount) a,SUM(pr.price_orginal*oprod.amount) b,SUM(pr.price_sell*oprod.amount) c,SUM(pr.price_sell*oprod.amount-pr.price_sell*oprod.amount*pr.tax-pr.price_orginal*oprod.amount) d ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("LEFT JOIN CUSTOMER cus ON cus.id = opro.fk_customer_id ");
		sql.append("LEFT JOIN EMPLOYEE emp ON emp.id = opro.fk_employee_id ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL oprod ON oprod.fk_order_id = opro.id ");
		sql.append("LEFT JOIN PRODUCT pr ON pr.id = oprod.fk_product_id ");
		sql.append("LEFT JOIN PRODUCT_GROUP prg ON prg.id = pr.fk_product_group_id ");
		sql.append("WHERE opro.status = 1 AND opro.is_delete = '0' ");
		if (!orderProductForm.getGroupProduct().equals("-1")) {
			sql.append("AND prg.id = " + orderProductForm.getGroupProduct() + " ");
		}
		if (!orderProductForm.getNameEmployee().equals("-1")) {
			sql.append("AND emp.id = " + orderProductForm.getNameEmployee() + " ");
		}
		if (!orderProductForm.getDate().equals("")) {
			sql.append("AND DATEDIFF(year, opro.date_time,'" + orderProductForm.getDate() + "') = 0 ");
			sql.append("AND DATEDIFF(month, opro.date_time,'" + orderProductForm.getDate() + "') = 0 ");
			sql.append("AND DATEDIFF(day, opro.date_time,'" + orderProductForm.getDate() + "') = 0 ");
		}
		sql.append("GROUP BY pr.id");

		Query query = entityManager.createNativeQuery(sql.toString());
		// List<ReportOrderProduct> listValueOrderProduct=query.getResultList();
		List<Object[]> listValueOrderProductObject = query.getResultList();
		List<ReportOrderProduct> listValueOrderProduct = new ArrayList<ReportOrderProduct>();
		int i = 0;
		for (Object[] obj : listValueOrderProductObject) {
			if (listValueOrderProductObject.size() > 1) {
				if (i != 0) {
					ReportOrderProduct reportOrderProduct = new ReportOrderProduct(
							Double.valueOf(String.valueOf(obj[0])), Double.valueOf(String.valueOf(obj[1])),
							Double.valueOf(String.valueOf(obj[2])), Double.valueOf(String.valueOf(obj[3])));
					listValueOrderProduct.add(reportOrderProduct);
				}
			} else {
				ReportOrderProduct reportOrderProduct = new ReportOrderProduct(Double.valueOf(String.valueOf(obj[0])),
						Double.valueOf(String.valueOf(obj[1])), Double.valueOf(String.valueOf(obj[2])),
						Double.valueOf(String.valueOf(obj[3])));
				listValueOrderProduct.add(reportOrderProduct);
			}
			i++;
		}
		return listValueOrderProduct;
	}

	// Chart column
	@Override
	public void getListNameGroupProduct(String month, List<ReponseBean> listData) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT prg.product_group_name ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL oprod ON oprod.fk_order_id = opro.id ");
		sql.append("LEFT JOIN PRODUCT pr ON pr.id = oprod.fk_product_id ");
		sql.append("LEFT JOIN PRODUCT_GROUP prg ON prg.id = pr.fk_product_group_id ");
		sql.append("WHERE opro.status = 1 AND opro.is_delete = '0' ");
		if (!month.equals("-1")) {
			sql.append("AND MONTH(opro.date_time) = '" + month + "' ");
		}
		Query query = entityManager.createNativeQuery(sql.toString());
		List<String> list = query.getResultList();
		for (String obj : list) {
			// listData.add(new ReponseBean(String.valueOf(obj[0])));
			listData.add(new ReponseBean(obj));
		}
	}

	// Chart column
	@Override
	public void getSumAmountListNameGroupProduct(String month, List<ReponseBean> listData) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(oprod.amount*0.1) ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL oprod ON oprod.fk_order_id = opro.id ");
		sql.append("LEFT JOIN PRODUCT pr ON pr.id = oprod.fk_product_id ");
		sql.append("LEFT JOIN PRODUCT_GROUP prg ON prg.id = pr.fk_product_group_id ");
		sql.append("WHERE opro.status = 1 AND opro.is_delete = '0' ");
		if (!month.equals("-1")) {
			sql.append("AND MONTH(opro.date_time) = '" + month + "' ");
		}
		sql.append("GROUP BY prg.id ");
		Query query = entityManager.createNativeQuery(sql.toString());
		List<BigDecimal> list = query.getResultList();
		for (int i = 0; i < list.size(); i++) {
			listData.get(i).setY(list.get(i));
		}
	}

	// Chart line
	@Override
	public void getListMonthDate(String year, List<ReponseBean> listData) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT MONTH(opro.date_time) ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("WHERE opro.status = 1 AND opro.is_delete = '0' ");
		sql.append("AND YEAR(opro.date_time) = '" + year + "' ");

		Query query = entityManager.createNativeQuery(sql.toString());
		List<Integer> list = query.getResultList();
		for (Integer obj : list) {
			listData.add(new ReponseBean(String.valueOf(obj)));
		}
	}

	// Chart line
	@Override
	public void getValueFinance(String year, List<ReponseBean> listData) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(opd.amount) ");
		sql.append("FROM ORDER_PRODUCT op ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL opd ");
		sql.append("ON opd.fk_order_id = op.id ");
		sql.append("WHERE op.status = 1 AND op.is_delete = '0' ");
		sql.append("AND YEAR(op.date_time) = '" + year + "' ");
		sql.append("GROUP BY(MONTH(op.date_time)) ");

		Query query = entityManager.createNativeQuery(sql.toString());
		List<Integer> list = query.getResultList();
		for (int i = 0; i < list.size(); i++) {
			listData.get(i).setY(new BigDecimal(list.get(i)));
		}
	}

	@Override
	public void getQuy(String param, List<ReponseBean> listData) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT prg.product_group_name ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL oprod ON oprod.fk_order_id = opro.id ");
		sql.append("LEFT JOIN PRODUCT pr ON pr.id = oprod.fk_product_id ");
		sql.append("LEFT JOIN PRODUCT_GROUP prg ON prg.id = pr.fk_product_group_id ");
		sql.append("WHERE opro.status = 1 AND opro.is_delete = '0' ");
		if (!param.equals("qu0")) {
			if (param.equals("qu1")) {
				sql.append(
						"AND MONTH(opro.date_time) = '1' OR MONTH(opro.date_time) = '2' OR MONTH(opro.date_time) = '3' ");
			} else if (param.equals("qu2")) {
				sql.append(
						"AND MONTH(opro.date_time) = '4' OR MONTH(opro.date_time) = '5' OR MONTH(opro.date_time) = '6' ");
			} else if (param.equals("qu3")) {
				sql.append(
						"AND MONTH(opro.date_time) = '7' OR MONTH(opro.date_time) = '8' OR MONTH(opro.date_time) = '9' ");
			} else if (param.equals("qu4")) {
				sql.append(
						"AND MONTH(opro.date_time) = '10' OR MONTH(opro.date_time) = '11' OR MONTH(opro.date_time) = '12' ");
			}

		}
		Query query = entityManager.createNativeQuery(sql.toString());
		List<String> list = query.getResultList();
		for (String obj : list) {
			// listData.add(new ReponseBean(String.valueOf(obj[0])));
			listData.add(new ReponseBean(obj));
		}
	}

	@Override
	public void getValueQuy(String param, List<ReponseBean> listData) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(oprod.amount*0.1) ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL oprod ON oprod.fk_order_id = opro.id ");
		sql.append("LEFT JOIN PRODUCT pr ON pr.id = oprod.fk_product_id ");
		sql.append("LEFT JOIN PRODUCT_GROUP prg ON prg.id = pr.fk_product_group_id ");
		sql.append("WHERE opro.status = 1 AND opro.is_delete = '0' ");
		if (!param.equals("qu0")) {
			if (param.equals("qu1")) {
				sql.append(
						"AND MONTH(opro.date_time) = '1' OR MONTH(opro.date_time) = '2' OR MONTH(opro.date_time) = '3' ");
			} else if (param.equals("qu2")) {
				sql.append(
						"AND MONTH(opro.date_time) = '4' OR MONTH(opro.date_time) = '5' OR MONTH(opro.date_time) = '6' ");
			} else if (param.equals("qu3")) {
				sql.append(
						"AND MONTH(opro.date_time) = '7' OR MONTH(opro.date_time) = '8' OR MONTH(opro.date_time) = '9' ");
			} else if (param.equals("qu4")) {
				sql.append(
						"AND MONTH(opro.date_time) = '10' OR MONTH(opro.date_time) = '11' OR MONTH(opro.date_time) = '12' ");
			}

		}
		sql.append("GROUP BY prg.id ");
		Query query = entityManager.createNativeQuery(sql.toString());
		List<BigDecimal> list = query.getResultList();
		for (int i = 0; i < list.size(); i++) {
			listData.get(i).setY(list.get(i));
		}
	}

	@Override
	public void getYear(String param, List<ReponseBean> listData) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT prg.product_group_name ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL oprod ON oprod.fk_order_id = opro.id ");
		sql.append("LEFT JOIN PRODUCT pr ON pr.id = oprod.fk_product_id ");
		sql.append("LEFT JOIN PRODUCT_GROUP prg ON prg.id = pr.fk_product_group_id ");
		sql.append("WHERE opro.status = 1 AND opro.is_delete = '0' ");
		if (!param.equals("1000")) {
			sql.append("AND YEAR(opro.date_time) = '" + param + "' ");
		}
		Query query = entityManager.createNativeQuery(sql.toString());
		List<String> list = query.getResultList();
		for (String obj : list) {
			// listData.add(new ReponseBean(String.valueOf(obj[0])));
			listData.add(new ReponseBean(obj));
		}
	}

	@Override
	public void getValueYear(String param, List<ReponseBean> listData) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(oprod.amount*0.1) ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL oprod ON oprod.fk_order_id = opro.id ");
		sql.append("LEFT JOIN PRODUCT pr ON pr.id = oprod.fk_product_id ");
		sql.append("LEFT JOIN PRODUCT_GROUP prg ON prg.id = pr.fk_product_group_id ");
		sql.append("WHERE opro.status = 1 AND opro.is_delete = '0' ");
		if (!param.equals("1000")) {
			sql.append("AND YEAR(opro.date_time) = '" + param + "' ");
		}
		sql.append("GROUP BY prg.id ");
		Query query = entityManager.createNativeQuery(sql.toString());
		List<BigDecimal> list = query.getResultList();
		for (int i = 0; i < list.size(); i++) {
			listData.get(i).setY(list.get(i));
		}
	}

	@Override
	@Transactional
	public void addCart(String idOrder, String id, int amount) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ORDER_PRODUCT_DETAIL(fk_order_id,fk_product_id,amount) ");
		sql.append("VALUES(?,?,1)");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idOrder).setParameter(2, id).executeUpdate();
	}

	@Override
	@Transactional
	public void deleteCart(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ORDER_PRODUCT_DETAIL WHERE id = " + id);
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
	}

	@Override
	public List<CartBean> getListCart(String idLogin, int status) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT odd.id a,odd.amount,pro.id,pro.name,pro.price_sell,pro.note,pro.color,pro.weight,pro.picture,od.id b,od.total_money,odd.amount*pro.price_sell,od.is_pay_pal ");
		sql.append("FROM ORDER_PRODUCT od ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL odd ON odd.fk_order_id = od.id ");
		sql.append("LEFT JOIN PRODUCT pro ON pro.id = odd.fk_product_id ");
		sql.append("WHERE od.fk_customer_id = '" + idLogin + "' ");
		if (status == 0) {
			sql.append("AND od.status = 0 ");
		} else {
			sql.append("AND od.id = " + status);
		}
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listProductObject = query.getResultList();
		List<CartBean> listProduct = new ArrayList<CartBean>();
		if (listProductObject.size() != 0) {
			for (Object[] obj : listProductObject) {
				String id = String.valueOf(obj[0]), amount = String.valueOf(obj[1]), idpro = String.valueOf(obj[2]);
				if (!id.equals("null") && !amount.equals("null") && !idpro.equals("null")
						&& !String.valueOf(obj[11]).equals("null")) {
					CartBean cartBean = new CartBean(Integer.parseInt(String.valueOf(id)),
							Integer.parseInt(String.valueOf(amount)), Integer.parseInt(String.valueOf(idpro)),
							String.valueOf(obj[3]), String.valueOf(obj[4]), String.valueOf(obj[5]),
							String.valueOf(obj[6]), String.valueOf(obj[7]), String.valueOf(obj[8]),
							String.valueOf(obj[9]), String.valueOf(obj[10]), String.valueOf(obj[11]),
							String.valueOf(obj[12]));
					listProduct.add(cartBean);
				}

			}
		}

		return listProduct;
	}

	@Override
	public int checkCart(String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM ORDER_PRODUCT WHERE fk_customer_id = '" + idLogin + "' and status = 0");
		Query query = entityManager.createNativeQuery(sql.toString());
		return (int) query.getSingleResult();
	}

	@Override
	@Transactional
	public void updateMoneyCart(String idOrder, String id, int amount, double price) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ORDER_PRODUCT SET total_money ");
		if (amount == 1) {
			sql.append("= (SELECT total_money FROM ORDER_PRODUCT WHERE id = " + idOrder
					+ ") + (SELECT price_sell FROM PRODUCT WHERE PRODUCT.id = " + id + ")");
		} else {
			sql.append("= " + price);
		}
		sql.append(" WHERE id = " + idOrder);
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
	}

	@Override
	@Transactional
	public int insertOrderCart(String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO ORDER_PRODUCT(date_time,status,fk_customer_id,is_delete,total_money) VALUES(GETDATE(),0,"
						+ idLogin + ",0,0) ");

		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
		String id = "SELECT MAX(id) FROM ORDER_PRODUCT";
		Query query1 = entityManager.createNativeQuery(id.toString());
		List<Object> listObject = query1.getResultList();
		int value = 0;
		for (Object obj : listObject) {
			value = Integer.parseInt(String.valueOf(obj));
		}
		return value;
	}

	@Override
	@Transactional
	public void updateStatusOrder(String idLogin, String status) {
		if (status.equals("0")) {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ORDER_PRODUCT SET status = '1' ");
			sql.append("WHERE fk_customer_id = " + idLogin);
			Query query = entityManager.createNativeQuery(sql.toString());
			query.executeUpdate();
		} else {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ORDER_PRODUCT_DETAIL SET amount = " + status);
			sql.append(" WHERE id = " + idLogin);
			Query query = entityManager.createNativeQuery(sql.toString());
			query.executeUpdate();
		}
	}

	@Override
	public List<TOrderProduct> getTotalMoney(String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT opro.* ");
		sql.append("FROM         ORDER_PRODUCT opro ");
		sql.append("WHERE fk_customer_id ='" + idLogin + "' ");
		sql.append("AND status = '0'");

		Query query = entityManager.createNativeQuery(sql.toString(), TOrderProduct.class);
		List<TOrderProduct> list = query.getResultList();
		return list;
	}

	@Override
	@Transactional
	public void updateStatusPayment(String idOrder) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ORDER_PRODUCT SET is_pay_pal = '1' ");
		sql.append("WHERE id = '" + idOrder + "'");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
	}

	@Override
	public List<OrderCustomerBean> getListOrderCustomer(String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT od.id,del.date_start ");
		sql.append("FROM ORDER_PRODUCT od ");
		sql.append("LEFT JOIN DELIVERY del ON del.fk_order_id = od.id ");
		sql.append("WHERE od.fk_customer_id = '" + idLogin + "' AND od.status = '1'");

		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listObject = query.getResultList();
		List<OrderCustomerBean> list = new ArrayList<OrderCustomerBean>();
		if (listObject.size() != 0) {
			for (Object[] obj : listObject) {
				OrderCustomerBean orderCustomerBean = new OrderCustomerBean(String.valueOf(obj[0]),
						String.valueOf(obj[1]));
				list.add(orderCustomerBean);
			}
		}

		return list;
	}

	@Override
	public List<String> getListOrderProduct(String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT pr.name ");
		sql.append("FROM         ORDER_PRODUCT_DETAIL opd ");
		sql.append("LEFT JOIN PRODUCT pr ON pr.id = opd.fk_product_id ");
		sql.append("WHERE opd.fk_order_id = '" + idLogin + "'");
		Query query = entityManager.createNativeQuery(sql.toString());
		List<String> list = query.getResultList();
		return list;
	}

	@Override
	public List<OrderCustomerBean> getOrderDetail(String idOrder) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT DISTINCT od.id,cus.name,ad.address_detail,od.is_pay_pal,del.status,convert(varchar,del.date_start,23) date_start,convert(varchar,del.date_pay,23),convert(varchar,del.date_delivery,23) date_delivery,convert(varchar,del.date_end,23) date_end ");
		sql.append("FROM ORDER_PRODUCT od ");
		sql.append("LEFT JOIN DELIVERY del ON del.fk_order_id = od.id ");
		sql.append("LEFT JOIN ORDER_PRODUCT_DETAIL odd ON odd.fk_order_id = od.id ");
		sql.append("LEFT JOIN CUSTOMER cus ON cus.id = od.fk_customer_id ");
		sql.append("LEFT JOIN ADDRESS ad ON ad.fk_customer_id = cus.id ");
		sql.append("WHERE od.id = '" + idOrder + "'");

		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listObject = query.getResultList();
		List<OrderCustomerBean> list = new ArrayList<OrderCustomerBean>();
		if (listObject.size() != 0) {
			for (Object[] obj : listObject) {
				OrderCustomerBean orderCustomerBean = new OrderCustomerBean(String.valueOf(obj[0]),
						String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]),
						String.valueOf(obj[5]), String.valueOf(obj[6]), String.valueOf(obj[7]), String.valueOf(obj[8]));
				list.add(orderCustomerBean);
			}
		}

		return list;
	}

	@Override
	@Transactional
	public void updateStatusOrderAdmin(OrderCustomerBean orderCustomerBean) {
		// Update table ORDER_PRODUCT
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DELIVERY ");
		if (!orderCustomerBean.getStatus().equals("")) {
			sql.append("SET status = '" + orderCustomerBean.getStatus() + "'");
		}
		if (!orderCustomerBean.getDate_start().equals("")) {
			sql.append(",date_start = '" + orderCustomerBean.getDate_start() + "'");
		}
		if (!orderCustomerBean.getDate_pay().equals("")) {
			sql.append(",date_pay = '" + orderCustomerBean.getDate_pay() + "'");
		}
		if (!orderCustomerBean.getDate_delivery().equals("")) {
			sql.append(",date_delivery = '" + orderCustomerBean.getDate_delivery() + "'");
		}
		if (!orderCustomerBean.getDate_end().equals("")) {
			sql.append(",date_end = '" + orderCustomerBean.getDate_end() + "'");
		}
		sql.append("WHERE fk_order_id = " + orderCustomerBean.getId() + "");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();

	}

}
