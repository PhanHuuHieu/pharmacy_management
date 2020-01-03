package pharmacy.management.respository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.entity.TEmployee;
import pharmacy.management.form.EmployeeForm;
import pharmacy.management.respository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl extends BaseRepositoryImpl<TEmployee, Integer> implements EmployeeRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TEmployee> getListEmployee() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT emp.* ");
		sql.append("FROM         EMPLOYEE emp ");

		Query query = entityManager.createNativeQuery(sql.toString(), TEmployee.class);

		List<TEmployee> listEmployee = query.getResultList();
		return listEmployee;
	}

	@Override
	public String getNameEmployee(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT emp.name ");
		sql.append("FROM         EMPLOYEE emp ");
		sql.append("WHERE        emp.id = " + id);

		Query query = entityManager.createNativeQuery(sql.toString());
		String name = (String) query.getSingleResult();
		return name;
	}

	@Override
	public List<TEmployee> findUserAccount(String userName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT emp.* ");
		sql.append("FROM         EMPLOYEE emp ");
		sql.append("WHERE        emp.name = '" + userName + "'");

		Query query = entityManager.createNativeQuery(sql.toString(), TEmployee.class);
		List<TEmployee> list = query.getResultList();
		return list;
	}

	@Override
	public List<TEmployee> getAccountWithIdLogin(String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT emp.* ");
		sql.append("FROM         EMPLOYEE emp ");
		sql.append("WHERE        emp.id = '" + idLogin + "'");

		Query query = entityManager.createNativeQuery(sql.toString(), TEmployee.class);
		List<TEmployee> listCustomer = query.getResultList();
		return listCustomer;
	}

	@Override
	@Transactional
	public void editInforAccount(EmployeeForm customerForm, String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE EMPLOYEE SET name = N'" + customerForm.getName() + "',email = '" + customerForm.getEmail()
				+ "',phone_number = '" + customerForm.getPhoneNumber() + "' ");
		sql.append("WHERE id = " + idLogin);
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
	}
}
