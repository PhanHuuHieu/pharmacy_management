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
import org.springframework.transaction.annotation.Transactional;

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TEmployee;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.ProductForm;
import pharmacy.management.respository.EmployeeRepository;
import pharmacy.management.respository.ProductRepository;

@Repository
public class EmployeeRepositoryImpl extends BaseRepositoryImpl<TEmployee, Integer> implements EmployeeRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<TEmployee> getListEmployee() {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  DISTINCT emp.* ");
	    sql.append("FROM         EMPLOYEE emp ");
	    
	    Query query = entityManager.createNativeQuery(
	    		sql.toString(),TEmployee.class);
		
		List<TEmployee> listEmployee=query.getResultList();
	    return listEmployee;
	}

	@Override
	public String getNameEmployee(int id) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  DISTINCT emp.name ");
	    sql.append("FROM         EMPLOYEE emp ");
	    sql.append("WHERE        emp.id = "+id);
	    
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    String name=(String) query.getSingleResult();
	    return name;
	}
}
