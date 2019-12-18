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
import pharmacy.management.entity.TUnit;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.respository.ProductTypeRepository;
import pharmacy.management.respository.UnitRepository;

@Repository
public class UnitRepositoryImpl extends BaseRepositoryImpl<TUnit, Integer> implements UnitRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<TUnit> getListUnit() {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  DISTINCT unit.* ");
	    sql.append("FROM         UNIT unit ");
	    
	    Query query = entityManager.createNativeQuery(
	    		sql.toString(),TUnit.class);	
		List<TUnit> listUnit=query.getResultList();
		return listUnit;
	}


}
