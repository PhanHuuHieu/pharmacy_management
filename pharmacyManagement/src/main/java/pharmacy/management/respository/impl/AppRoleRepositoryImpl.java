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
import pharmacy.management.entity.TAddress;
import pharmacy.management.entity.TAppRole;
import pharmacy.management.entity.TCustomer;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.respository.AddressRepository;
import pharmacy.management.respository.AppRoleRepository;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;

@Repository
public class AppRoleRepositoryImpl extends BaseRepositoryImpl<TAppRole, Integer> implements AppRoleRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TAppRole> getNameRole(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT role.* ");
		sql.append("FROM         APP_ROLE role ");
		sql.append("LEFT JOIN USER_ROLE user_role ON user_role.role_id = role.role_id ");
		sql.append("WHERE        user_role.user_id = '" + userId + "'");

		Query query = entityManager.createNativeQuery(sql.toString(), TAppRole.class);
		List<TAppRole> listCustomer = query.getResultList();
		return listCustomer;
	}

}
