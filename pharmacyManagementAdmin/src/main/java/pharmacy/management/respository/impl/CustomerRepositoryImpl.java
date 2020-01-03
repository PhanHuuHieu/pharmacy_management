package pharmacy.management.respository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.bean.AccountBean;
import pharmacy.management.entity.TCustomer;
import pharmacy.management.respository.CustomersRepository;

@Repository
public class CustomerRepositoryImpl extends BaseRepositoryImpl<TCustomer, Integer> implements CustomersRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TCustomer> getListCustomer() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT cus.* ");
		sql.append("FROM         CUSTOMER cus ");

		Query query = entityManager.createNativeQuery(sql.toString(), TCustomer.class);

		List<TCustomer> listCustomer = query.getResultList();
		return listCustomer;
	}

	@Override
	public List<AccountBean> gettListAccount(String username, String email, String phoneNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT  DISTINCT cus.id,cus.name,cus.email, cus.phone_number,convert(varchar,cus.created_date,23),cus.point,cus.is_block,cus.is_block x,cus.id c ");
		sql.append("FROM         CUSTOMER cus ");
		sql.append("WHERE is_delete = '0' ");
		if (!username.equals("")) {
			sql.append("AND dbo.ufn_removeMark(name) LIKE '%" + username + "%' or name LIKE N'%" + username + "%' ");
		}
		if (!email.equals("")) {
			sql.append("AND email LIKE N'%" + email + "%' ");
		}
		if (!phoneNumber.equals("")) {
			sql.append("AND phone_number LIKE N'%" + phoneNumber + "%' ");
		}
		Query query = entityManager.createNativeQuery(sql.toString());
		List<AccountBean> listAccount = query.getResultList();
		return listAccount;
	}

	@Override
	@Transactional
	public void blockAccount(String idAccount, String check) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CUSTOMER SET modified_date = GETDATE(), ");
		if (check.equals("0")) {
			sql.append("is_block = '0' ");
		} else {
			sql.append("is_block = '1' ");
		}
		sql.append("WHERE id = " + idAccount);
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void deleteAccount(String idAccount) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CUSTOMER SET is_delete = '1', modified_date = GETDATE() ");
		sql.append("WHERE id = " + idAccount);
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
	}

}
