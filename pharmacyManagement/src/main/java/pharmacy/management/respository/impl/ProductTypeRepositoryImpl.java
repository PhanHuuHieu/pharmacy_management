package pharmacy.management.respository.impl;

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
import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TProductType;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.respository.ProductTypeRepository;

@Repository
public class ProductTypeRepositoryImpl extends BaseRepositoryImpl<TProductType, Integer> implements ProductTypeRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<TProductType> getListProductType() {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  DISTINCT pro_typ.* ");
	    sql.append("FROM         PRODUCT_TYPE pro_typ ");
	    
	    Query query = entityManager.createNativeQuery(
	    		sql.toString(),TProductType.class);	
		List<TProductType> listProductType=query.getResultList();
		return listProductType;
	}


}
