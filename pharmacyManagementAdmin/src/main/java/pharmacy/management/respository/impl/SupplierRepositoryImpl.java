package pharmacy.management.respository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.entity.TSupplier;
import pharmacy.management.form.SupplierForm;
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

		Query query = entityManager.createNativeQuery(sql.toString(), TSupplier.class);
		List<TSupplier> listProductType = query.getResultList();
		return listProductType;
	}

	@Override
	public List<SupplierForm> getListSupplierSearch(SupplierForm supplierForm) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT sup.id,sup.supplier_name,sup.phone_number,sup.supplier_address,sup.id c ");
		sql.append("FROM         SUPPLIER sup ");
		sql.append("WHERE sup.is_delete = '0' ");
		if (!String.valueOf(supplierForm.getId()).equals("")) {
			sql.append("AND id = " + supplierForm.getId() + " ");
		}
		if (!supplierForm.getPhone_number().equals("")) {
			sql.append("AND phone_number = '" + supplierForm.getPhone_number() + "' ");
		}
		if (!supplierForm.getSupplier_name().equals("")) {
			sql.append("AND dbo.ufn_removeMark(supplier_name) LIKE '%" + supplierForm.getSupplier_name()
					+ "%' or supplier_name LIKE N'%" + supplierForm.getSupplier_name() + "%' ");
		}
		Query query = entityManager.createNativeQuery(sql.toString());

		List<SupplierForm> list = query.getResultList();
		return list;
	}

	@Override
	public List<TSupplier> getSupplier(String idSupplier) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT sup.id,sup.supplier_name,sup.phone_number,sup.supplier_address ");
		sql.append("FROM         SUPPLIER sup ");
		sql.append("WHERE sup.id = ?1 ");

		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idSupplier);
		List<TSupplier> list = query.getResultList();
		return list;
	}

	@Override
	@Transactional
	public void deleteSupplier(String idSupplier) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SUPPLIER ");
		sql.append("SET is_delete = '1',modified_date = GETDATE() ");
		sql.append("WHERE id = ?1");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idSupplier).executeUpdate();
	}

	@Override
	public long countIdSupplier(String idSupplier) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  COUNT(sup.id) ");
		sql.append("FROM    SUPPLIER sup ");
		sql.append("WHERE sup.id = ?1 ");
		sql.append("AND sup.is_delete = '0' ");

		Query query = entityManager.createQuery(sql.toString());
		query.setParameter(1, Integer.parseInt(idSupplier));
		return (long) query.getSingleResult();
	}

	@Override
	@Transactional
	public void updateSupplier(TSupplier tSupplier) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SUPPLIER SET  supplier_name= ?1,phone_number = ?2,supplier_address = ?3 ");
		sql.append("WHERE id = ?4");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, tSupplier.getSupplier_name()).setParameter(2, tSupplier.getPhone_number())
				.setParameter(3, tSupplier.getSupplier_address()).setParameter(4, tSupplier.getId()).executeUpdate();
	}

	@Override
	@Transactional
	public void insertSupplier(TSupplier tSupplier) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO SUPPLIER(supplier_name,phone_number,supplier_address,is_delete,created_date) ");
		sql.append("VALUES(?,?,?,'0',GETDATE()) ");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, tSupplier.getSupplier_name()).setParameter(2, tSupplier.getPhone_number())
				.setParameter(3, tSupplier.getSupplier_address()).executeUpdate();
	}

}
