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

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.bean.OrderProductBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.respository.ProductGroupRepository;
import pharmacy.management.respository.ProductRepository;

@Repository
public class ProductGroupRepositoryImpl extends BaseRepositoryImpl<TProductGroup, Integer>
		implements ProductGroupRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TProductGroup> getListProductGroup() {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT pro_gro.* ");
		sql.append("FROM         PRODUCT_GROUP pro_gro ");

		Query query = entityManager.createNativeQuery(sql.toString(), TProductGroup.class);
		List<TProductGroup> listProductGroup = query.getResultList();
		return listProductGroup;
	}

	@Override
	public String getNameProductGroup(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT pro_gro.product_group_name ");
		sql.append("FROM         PRODUCT_GROUP pro_gro ");
		sql.append("WHERE        pro_gro.id = " + id);

		Query query = entityManager.createNativeQuery(sql.toString());
		return (String) query.getSingleResult();
	}

	@Override
	public List<TProduct> getListProductGroupById(String id, int newProduct) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT  DISTINCT pro.id,pro.name,pro.note,pro.price_sell,pro.picture,pro.description,pro.weight,pro.color ");
		sql.append("FROM         PRODUCT pro ");
		sql.append("LEFT JOIN PRODUCT_GROUP prog ON prog.id = pro.fk_product_group_id ");
		sql.append("WHERE        pro.status = 0 ");
		if (newProduct == 1) {
			sql.append("AND        pro.created_date >= getdate()-4");
		} else {
			sql.append("AND        pro.fk_product_group_id = " + id);
		}
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listProductObject = query.getResultList();
		List<TProduct> listProduct = new ArrayList<TProduct>();
		for (Object[] obj : listProductObject) {
			TProduct tProduct = new TProduct(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]),
					String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]), String.valueOf(obj[5]),
					String.valueOf(obj[6]), String.valueOf(obj[7]));
			listProduct.add(tProduct);
		}
		return listProduct;
	}

	@Override
	public List<TProductGroup> getListTopProductGroup() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DISTINCT pro_gro.* ");
		sql.append("FROM         PRODUCT_GROUP pro_gro ");
		sql.append("WHERE pro_gro.[top] = '1' ");

		Query query = entityManager.createNativeQuery(sql.toString(), TProductGroup.class);
		List<TProductGroup> listProductGroup = query.getResultList();
		return listProductGroup;
	}

	@Override
	public List<TProduct> getListProductInIndex() {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT  DISTINCT pro.id,pro.name,pro.note,pro.price_sell,pro.picture,pro.description,pro.weight,pro.color ");
		sql.append("FROM         PRODUCT pro ");
		sql.append("LEFT JOIN PRODUCT_GROUP prog ON prog.id = pro.fk_product_group_id ");
		sql.append("WHERE        pro.status = 0 ");

		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listProductObject = query.getResultList();
		List<TProduct> listProduct = new ArrayList<TProduct>();
		for (Object[] obj : listProductObject) {
			TProduct tProduct = new TProduct(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]),
					String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]), String.valueOf(obj[5]),
					String.valueOf(obj[6]), String.valueOf(obj[7]));
			listProduct.add(tProduct);
		}
		return listProduct;
	}

	@Override
	public List<TProduct> getListProductRelated(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT DISTINCT pro.id,pro.name,pro.note, pro.price_sell,pro.picture,pro.description,pro.weight,pro.color ");
		sql.append("FROM PRODUCT pro ");
		sql.append(
				"WHERE pro.fk_product_group_id = (SELECT fk_product_group_id FROM PRODUCT WHERE id = ?1) AND pro.status = 0");

		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, id);
		List<Object[]> listProductObject = query.getResultList();
		List<TProduct> listProduct = new ArrayList<TProduct>();
		for (Object[] obj : listProductObject) {
			TProduct tProduct = new TProduct(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]),
					String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]), String.valueOf(obj[5]),
					String.valueOf(obj[6]), String.valueOf(obj[7]));
			listProduct.add(tProduct);
		}
		return listProduct;
	}

	@Override
	public List<TProduct> getListSearch(String id, String[] searchUnit, String[] searchSup, String nameSearch) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT  DISTINCT pro.id,pro.name,pro.note,pro.price_sell,pro.picture,pro.description,pro.weight,pro.color ");
		sql.append("FROM         PRODUCT pro ");
		sql.append("LEFT JOIN PRODUCT_GROUP prog ON prog.id = pro.fk_product_group_id ");
		sql.append("WHERE        pro.status = 0 ");
		if (id != null) {
			sql.append("AND        pro.fk_product_group_id = " + id);
		}

		if (!nameSearch.equals("")) {
			sql.append(
					"AND dbo.ufn_removeMark(name) LIKE '%" + nameSearch + "%' or name LIKE N'%" + nameSearch + "%' ");
		}
		if (!searchUnit[0].equals("")) {
			for (int i = 0; i < searchUnit.length; i++) {
				if (i == 0) {
					sql.append(" AND        pro.fk_unit_id = " + searchUnit[i]);
				} else {
					sql.append(" OR        pro.fk_unit_id = " + searchUnit[i]);
				}
			}
		}
		if (!searchSup[0].equals("")) {
			for (int i = 0; i < searchSup.length; i++) {
				if (i == 0) {
					sql.append(" AND        pro.fk_supplier_id = " + searchSup[i]);
				} else {
					sql.append(" OR        pro.fk_supplier_id = " + searchSup[i]);
				}
			}
		}
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listProductObject = query.getResultList();
		List<TProduct> listProduct = new ArrayList<TProduct>();
		for (Object[] obj : listProductObject) {
			TProduct tProduct = new TProduct(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]),
					String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]), String.valueOf(obj[5]),
					String.valueOf(obj[6]), String.valueOf(obj[7]));
			listProduct.add(tProduct);
		}
		return listProduct;
	}

}
