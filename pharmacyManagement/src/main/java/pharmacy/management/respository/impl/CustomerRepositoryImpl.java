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
import pharmacy.management.entity.TCustomer;
import pharmacy.management.entity.TEmployee;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.CustomerForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.respository.CustomersRepository;
import pharmacy.management.respository.EmployeeRepository;
import pharmacy.management.respository.ProductRepository;

@Repository
public class CustomerRepositoryImpl extends BaseRepositoryImpl<TCustomer, Integer> implements CustomersRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<TCustomer> getListCustomer() {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  DISTINCT cus.* ");
	    sql.append("FROM         CUSTOMER cus ");
	    
	    Query query = entityManager.createNativeQuery(
	    		sql.toString(),TCustomer.class);
		
		List<TCustomer> listCustomer=query.getResultList();
	    return listCustomer;
	}

	@Transactional
	@Override
	public void registerCustomer(CustomerForm customerForm) {
		StringBuilder sql = new StringBuilder();
	    sql.append("INSERT INTO CUSTOMER(phone_number,name,email,password,created_date) ");
	    sql.append("VALUES(?,?,?,?,GETDATE()) ");
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    query
	    .setParameter(1, customerForm.getPhone_number())
	    .setParameter(2, customerForm.getName())
	    .setParameter(3, customerForm.getEmail())
	    .setParameter(4, customerForm.getPassword())
	    .executeUpdate();
	}
	
}
