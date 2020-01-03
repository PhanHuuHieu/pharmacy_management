package pharmacy.management.respository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.bean.OrderProductBean;
import pharmacy.management.bean.OrderCustomerBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.PurchaseBean;
import pharmacy.management.entity.TAddress;
import pharmacy.management.entity.TDelivery;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.respository.AddressRepository;
import pharmacy.management.respository.DeliveryRepository;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;

@Repository
public class DeliveryRepositoryImpl extends BaseRepositoryImpl<TDelivery, Integer> implements DeliveryRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public void insertDelivery(String idOrder) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO DELIVERY(date_start, date_end, date_pay, date_delivery, status,is_delete,is_return,fk_order_id) ");
		sql.append("VALUES(GETDATE(),GETDATE()+4,GETDATE()+1,GETDATE()+2,'0','0','0',?) ");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idOrder).executeUpdate();
	}

	@Override
	public List<TDelivery> getListDelivery(String idOrder) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT del.* ");
		sql.append("FROM DELIVERY del ");
		sql.append("WHERE del.fk_order_id = '" + idOrder + "'");

		Query query = entityManager.createNativeQuery(sql.toString(), TDelivery.class);
		List<TDelivery> list = query.getResultList();

		return list;
	}

}
