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
import pharmacy.management.entity.TSupplier;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.respository.ProductTypeRepository;
import pharmacy.management.respository.SupplierRepository;

@Repository
public class SupplierRepositoryImpl extends BaseRepositoryImpl<TSupplier, Integer> implements SupplierRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<TSupplier> getListSupplier() {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  DISTINCT sup.* ");
	    sql.append("FROM         SUPPLIER sup ");
	    
	    Query query = entityManager.createNativeQuery(
	    		sql.toString(),TSupplier.class);	
		List<TSupplier> listProductType=query.getResultList();
		return listProductType;
	}


}
