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
import pharmacy.management.form.CustomerForm;
import pharmacy.management.form.RegisterForm;
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

	@Transactional
	@Override
	public void registerCustomer(RegisterForm registerForm) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO CUSTOMER(phone_number,name,email,password,created_date,created_by,modified_by,point) ");
		sql.append("VALUES(?,?,?,?,GETDATE(),1,1,0) ");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, registerForm.getPhone_number()).setParameter(2, registerForm.getUser_name())
				.setParameter(3, registerForm.getEmail()).setParameter(4, registerForm.getPassword()).executeUpdate();

		List<TCustomer> list = this.findUserAccount(registerForm.getUser_name());
		StringBuilder sql1 = new StringBuilder();
		sql1.append("INSERT INTO USER_ROLE(user_id,role_id) ");
		sql1.append("VALUES('" + list.get(0).getId() + "',1) ");
		Query query1 = entityManager.createNativeQuery(sql1.toString());
		query1.executeUpdate();
	}

	@Override
	public List<TCustomer> getIdSocial(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT cus.* ");
		sql.append("FROM         CUSTOMER cus ");
		sql.append("WHERE cus.id_social = '" + id + "'");

		Query query = entityManager.createNativeQuery(sql.toString(), TCustomer.class);
		List<TCustomer> listCustomer = query.getResultList();
		return listCustomer;
	}

	@Override
	@Transactional
	public void insertCustomer(String name, String id_social) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO CUSTOMER(name,phone_number,password,id_social,created_date,created_by,modified_by,point) ");
		sql.append("VALUES(?,?,?,?,GETDATE(),?,?,0) ");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, name).setParameter(2, "").setParameter(3, "").setParameter(4, id_social)
				.setParameter(5, 1).setParameter(6, 1).executeUpdate();

		List<TCustomer> list = this.findUserAccount(name);
		StringBuilder sql1 = new StringBuilder();
		sql1.append("INSERT INTO USER_ROLE(user_id,role_id) ");
		sql1.append("VALUES('" + list.get(0).getId() + "',1) ");
		Query query1 = entityManager.createNativeQuery(sql1.toString());
		query1.executeUpdate();
	}

	@Override
	public List<TCustomer> findUserAccount(String userName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT cus.* ");
		sql.append("FROM         CUSTOMER cus ");
		sql.append("WHERE        cus.name = '" + userName + "'");

		Query query = entityManager.createNativeQuery(sql.toString(), TCustomer.class);
		List<TCustomer> listCustomer = query.getResultList();
		return listCustomer;
	}

	@Override
	public List<AccountBean> gettListAccount(String username, String email, String phoneNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT  DISTINCT cus.id,cus.name,cus.email, cus.phone_number,convert(varchar,cus.created_date,23),cus.point,cus.is_block,cus.is_block b,cus.id c ");
		sql.append("FROM         CUSTOMER cus ");
		sql.append("WHERE is_delete = '0' ");
		if (!username.equals("")) {
			sql.append("AND name LIKE N'%" + username + "%' ");
		}
		if (!email.equals("")) {
			sql.append("AND email LIKE N'%" + email + "%' ");
		}
		if (!phoneNumber.equals("")) {
			sql.append("AND phone_number LIKE N'%" + phoneNumber + "%' ");
		}
		Query query = entityManager.createNativeQuery(sql.toString());
		List<AccountBean> listAccount = query.getResultList();
//	    List<Object[]> listAccountObject=query.getResultList();
//		List<AccountBean> listAccount = new ArrayList<AccountBean>();
//		for(Object[] obj : listAccountObject)
//		{
//			AccountBean accountBean = new AccountBean(Integer.parseInt(String.valueOf(obj[0])),
//					String.valueOf(obj[1]), String.valueOf(obj[2]), String.valueOf(obj[3]), 
//					String.valueOf(obj[4]), String.valueOf(obj[5]),
//					Integer.parseInt(String.valueOf(obj[6])),Integer.parseInt(String.valueOf(obj[7])));
//			listAccount.add(accountBean);
//		}
		return listAccount;
	}

	@Override
	@Transactional
	public void blockAccount(String idAccount, String check) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CUSTOMER SET modified_date = GETDATE(), ");
		if (check.equals("0")) {
			sql.append("is_block = '1' ");
		} else {
			sql.append("is_block = '0' ");
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

	@Override
	public List<TCustomer> getAccountWithEmail(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT cus.* ");
		sql.append("FROM         CUSTOMER cus ");
		sql.append("WHERE        cus.email = '" + email + "'");

		Query query = entityManager.createNativeQuery(sql.toString(), TCustomer.class);
		List<TCustomer> listCustomer = query.getResultList();
		return listCustomer;
	}

	@Override
	@Transactional
	public void updatePasswordNew(String password, String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CUSTOMER SET password = '" + password + "'");
		sql.append("WHERE id = " + idLogin);
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
	}

	@Override
	public List<TCustomer> getAccountWithIdLogin(String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT cus.* ");
		sql.append("FROM         CUSTOMER cus ");
		sql.append("WHERE        cus.id = '" + idLogin + "'");

		Query query = entityManager.createNativeQuery(sql.toString(), TCustomer.class);
		List<TCustomer> listCustomer = query.getResultList();
		return listCustomer;
	}

	@Override
	@Transactional
	public void editInforAccount(CustomerForm customerForm, String idLogin) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CUSTOMER SET name = N'" + customerForm.getName() + "',email = '" + customerForm.getEmail()
				+ "',phone_number = '" + customerForm.getPhoneNumber() + "',number_account = '"
				+ customerForm.getNumberAccount() + "' ");
		sql.append("WHERE id = " + idLogin);
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
	}

	@Override
	public List<TCustomer> getCustomerWithBarcode(String barcode) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT cus.* ");
		sql.append("FROM         CUSTOMER cus ");
		sql.append("WHERE cus.barcode = ?1 ");

		Query query = entityManager.createNativeQuery(sql.toString(), TCustomer.class);
		query.setParameter(1, barcode);
		// TProduct tProduct = (TProduct) query.getSingleResult();
		List<TCustomer> listCus = query.getResultList();
		return listCus;
	}

	@Override
	@Transactional
	public void updatePointCustomerLoyal(String idLogin, double totalMoney) {
		int point = 0;
		if (totalMoney > 0 && totalMoney <= 50) {
			point = 1;
		} else if (totalMoney > 50 && totalMoney <= 100) {
			point = 2;
		} else if (totalMoney > 100 && totalMoney <= 150) {
			point = 3;
		} else if (totalMoney > 150) {
			point = 4;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CUSTOMER SET point = point + " + point);
		sql.append("WHERE id = " + idLogin);
		Query query = entityManager.createNativeQuery(sql.toString());
		query.executeUpdate();
	}

}
