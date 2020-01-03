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

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;

@Repository
public class ProductGroupRepositoryImpl extends BaseRepositoryImpl<TProductGroup, Integer>
		implements ProductGroupRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TProductGroup> getListProductGroup() {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT pro_gro.* ");
		sql.append("FROM         PRODUCT_GROUP pro_gro ");

		Query query = entityManager.createNativeQuery(sql.toString(), TProductGroup.class);
		List<TProductGroup> listProductGroup = query.getResultList();
		return listProductGroup;
	}

	@Override
	public String getNameProductGroup(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT pro_gro.product_group_name ");
		sql.append("FROM         PRODUCT_GROUP pro_gro ");
		sql.append("WHERE        pro_gro.id = " + id);

		Query query = entityManager.createNativeQuery(sql.toString());
		return (String) query.getSingleResult();
	}
}
