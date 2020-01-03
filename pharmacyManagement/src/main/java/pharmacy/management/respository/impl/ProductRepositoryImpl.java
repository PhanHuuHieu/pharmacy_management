package pharmacy.management.respository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.entity.TProduct;
import pharmacy.management.respository.ProductRepository;

@Repository
public class ProductRepositoryImpl extends BaseRepositoryImpl<TProduct, Integer> implements ProductRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TProduct> getProductDisplay(int idProduct) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT  DISTINCT pro.id,pro.name,pro.note, pro.price_sell,pro.picture,pro.description,pro.weight,pro.color,pro.picture1,pro.picture2 ");
		sql.append("FROM         PRODUCT pro ");
		sql.append("WHERE pro.id = ?1 ");

		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idProduct);
		// TProduct tProduct = (TProduct) query.getSingleResult();
		// List<TProduct> listProduct=query.getResultList();
		List<Object[]> listProductObject = query.getResultList();
		List<TProduct> listProduct = new ArrayList<TProduct>();
		for (Object[] obj : listProductObject) {
			TProduct tProduct = new TProduct(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]),
					String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]), String.valueOf(obj[5]),
					String.valueOf(obj[6]), String.valueOf(obj[7]), String.valueOf(obj[8]), String.valueOf(obj[9]));
			listProduct.add(tProduct);
		}
		return listProduct;
	}
}
