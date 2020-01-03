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
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.PurchaseBean;
import pharmacy.management.entity.TAddress;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.respository.AddressRepository;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;

@Repository
public class AddressRepositoryImpl extends BaseRepositoryImpl<TAddress, Integer> implements AddressRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<PurchaseBean> getAddress(String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ad.name,ad.phone_number,ad.address_detail ");
		sql.append("FROM         ADDRESS ad ");
		sql.append("WHERE ad.fk_customer_id = " + idLogin);
		sql.append(" ORDER BY id DESC");

		Query query = entityManager.createNativeQuery(sql.toString());
		// List<PurchaseBean> listAddress=query.getResultList();
		List<Object[]> listAddressObj = query.getResultList();
		List<PurchaseBean> listAddress = new ArrayList<PurchaseBean>();
		int i = 0;
		for (Object[] obj : listAddressObj) {
			if (listAddressObj.size() == 1) {
				PurchaseBean purchaseBean = new PurchaseBean(String.valueOf(obj[0]), String.valueOf(obj[1]),
						String.valueOf(obj[2]));
				listAddress.add(purchaseBean);
			} else {
				if (i != 0) {
					PurchaseBean purchaseBean = new PurchaseBean(String.valueOf(obj[0]), String.valueOf(obj[1]),
							String.valueOf(obj[2]));
					listAddress.add(purchaseBean);
				}
				i++;
			}

		}
		return listAddress;
	}

	@Override
	@Transactional
	public void addAddress(PurchaseBean address, String idLogin, int status) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ADDRESS ");
		sql.append("SET address_detail = N'" + address.getAddress_detail() + "'");
		if (status == 0) {
			sql.append(",phone_number = '" + address.getPhone_number() + "',name = N'" + address.getName() + "' ");
		}
		sql.append("WHERE fk_customer_id = " + idLogin);
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void insertAddress(String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ADDRESS(name, phone_number, address_detail,fk_customer_id) ");
		sql.append("VALUES('','','',?) ");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idLogin).executeUpdate();
	}

}
