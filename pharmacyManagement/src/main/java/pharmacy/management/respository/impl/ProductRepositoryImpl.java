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
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.form.ProductForm;
import pharmacy.management.respository.ProductRepository;

@Repository
public class ProductRepositoryImpl extends BaseRepositoryImpl<TProduct, Integer> implements ProductRepository {

	@PersistenceContext
	EntityManager entityManager;
//	@Autowired
//	private SessionFactory sessionFactory;
	
	@Override
	public List<ProductBean> getListProduct() {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  DISTINCT pro.id, pro.name, price_orginal, price_sell, weight, color, product_type_name, product_group_name, pro.picture ");
	    sql.append("FROM         PRODUCT pro ");
	    sql.append("LEFT JOIN SUPPLIER sup ON sup.id = pro.fk_supplier_id ");
	    sql.append("LEFT JOIN PRODUCT_TYPE pro_type ON pro_type.id = pro.fk_product_type_id ");
	    sql.append("LEFT JOIN PRODUCT_GROUP pro_group ON pro_group.id = pro.fk_product_group_id ");
	    sql.append("LEFT JOIN UNIT uni ON uni.id = pro.fk_unit_id ");
	    sql.append("WHERE pro.status = '0' ");
	    
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
		
		List<ProductBean> listProduct=query.getResultList();
//	    SQLQuery query =sessionFactory.getCurrentSession().createNativeQuery(sql.toString());
//	    List<ProductBean> listProduct = query.list();
	    return listProduct;
	}

	@Override
	@Transactional
	public void insertProduct(TProduct tproduct) {
		StringBuilder sql = new StringBuilder();
	    sql.append("INSERT INTO PRODUCT(name,weight,color,date_manufature,date_expirate,price_sell,price_orginal,fk_supplier_id,fk_product_type_id,fk_product_group_id,fk_unit_id,note,picture,created_date) ");
	    sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE()) ");
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    query
	    .setParameter(1, tproduct.getName())
	    .setParameter(2, tproduct.getWeight())
	    .setParameter(3, tproduct.getColor())
	    .setParameter(4, tproduct.getDate_manufature())
	    .setParameter(5, tproduct.getDate_expirate())
	    .setParameter(6, tproduct.getPrice_sell())
	    .setParameter(7, tproduct.getPrice_orginal())
	    .setParameter(8, tproduct.getFk_supplier_id())
	    .setParameter(9, tproduct.getFk_product_type_id())
	    .setParameter(10, tproduct.getFk_product_group_id())
	    .setParameter(11, tproduct.getFk_unit_id())
	    .setParameter(12, tproduct.getNote())
	    .setParameter(13, tproduct.getPicture())
	    .executeUpdate();
	}

	@Override
	public List<TProduct> getProduct(int idProduct) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  DISTINCT pro.name, price_orginal, price_sell, weight, color, convert(varchar,date_manufature,23) date_manufature, convert(varchar,date_expirate, 23), fk_product_type_id, fk_product_group_id, fk_supplier_id, fk_unit_id, pro.note, pro.id,pro.picture,description ");
	    sql.append("FROM         PRODUCT pro ");
	    sql.append("WHERE pro.id = ?1 ");
	    
		Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    query.setParameter(1, idProduct);
	    //TProduct tProduct = (TProduct) query.getSingleResult();
	    List<TProduct> listProduct=query.getResultList();
	    return listProduct;
		//return (int)entityManager.createQuery(sql.toString()).getSingleResult();
	}

	@Override
	public long countIdProduct(int idProduct) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  COUNT(pro.id) ");
	    sql.append("FROM    PRODUCT pro ");
	    sql.append("WHERE pro.id = ?1 ");
	    sql.append("AND pro.status = '0' ");
	    
		Query query = entityManager.createQuery(
	    		sql.toString());
	    query.setParameter(1, idProduct);
	    return (long)query.getSingleResult();
	}

	@Override
	@Transactional
	public void updateProduct(TProduct tproduct) {
		StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE PRODUCT SET name = ?1,weight = ?2,color = ?3,date_manufature = ?4,date_expirate = ?5,");
	    sql.append("price_sell = ?6,price_orginal = ?7,fk_supplier_id = ?8,fk_product_type_id = ?9,fk_product_group_id = ?10,fk_unit_id = ?11,note = ?12,picture = ?13 ");
	    sql.append("WHERE id = ?14");
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    query
	    .setParameter(1, tproduct.getName())
	    .setParameter(2, tproduct.getWeight())
	    .setParameter(3, tproduct.getColor())
	    .setParameter(4, tproduct.getDate_manufature())
	    .setParameter(5, tproduct.getDate_expirate())
	    .setParameter(6, tproduct.getPrice_sell())
	    .setParameter(7, tproduct.getPrice_orginal())
	    .setParameter(8, tproduct.getFk_supplier_id())
	    .setParameter(9, tproduct.getFk_product_type_id())
	    .setParameter(10, tproduct.getFk_product_group_id())
	    .setParameter(11, tproduct.getFk_unit_id())
	    .setParameter(12, tproduct.getNote())
	    .setParameter(13, tproduct.getPicture())
	    .setParameter(14, tproduct.getId())
	    .executeUpdate();
	}

	@Override
	public List<ProductBean> getProductSearch(ProductForm productForm) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  DISTINCT pro.id, pro.name, price_orginal, price_sell, weight, color, product_type_name, product_group_name, pro.picture, pro.id c ");
	    sql.append("FROM         PRODUCT pro ");
	    sql.append("LEFT JOIN SUPPLIER sup ON sup.id = pro.fk_supplier_id ");
	    sql.append("LEFT JOIN PRODUCT_TYPE pro_type ON pro_type.id = pro.fk_product_type_id ");
	    sql.append("LEFT JOIN PRODUCT_GROUP pro_group ON pro_group.id = pro.fk_product_group_id ");
	    sql.append("LEFT JOIN UNIT uni ON uni.id = pro.fk_unit_id ");
	    sql.append("WHERE pro.status = '0' ");
	    if(productForm.getFk_product_group_id()!=-1)
	    {
	    	sql.append("AND fk_product_group_id = "+productForm.getFk_product_group_id()+" ");
	    }
	    if(productForm.getName()!=null)
	    {
	    	sql.append("AND pro.name LIKE N'%"+productForm.getName()+"' ");
	    }
	    if(productForm.getPrice_orginal()!=0)
	    {
	    	sql.append("AND price_orginal = "+productForm.getPrice_orginal()+" ");
	    }
	    if(productForm.getPrice_sell()!=0)
	    {
	    	sql.append("AND price_sell = "+productForm.getPrice_sell()+" ");
	    }
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
		
		List<ProductBean> listProduct=query.getResultList();
	    return listProduct;
	}

	@Override
	@Transactional
	public void THUXINHDEP(String id,String label) {
		StringBuilder sql = new StringBuilder();
	    //sql.append("UPDATE PRODUCT SET price_orginal = price_sell - price_sell/2 ");
		sql.append("UPDATE PRODUCT SET picture = ?1 ");
	    sql.append("WHERE id = ?2");
		//sql.append("INSERT INTO PRODUCT(name,fk_supplier_id,fk_product_type_id,fk_product_group_id,fk_unit_id) VALUES(?,1,1,1,1) ");
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    query
	    .setParameter(1, label)
	    .setParameter(2, id)
	    .executeUpdate();
	}

	@Override
	@Transactional
	public void deleteProduct(String id) {
		StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE PRODUCT ");
	    sql.append("SET status = '1',modified_date = GETDATE() ");
	    sql.append("WHERE id = ?1");
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    query
	    .setParameter(1, id)
	    .executeUpdate();
	}

	@Override
	public List<TProduct> getProductDisplay(int idProduct) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  DISTINCT pro.id,pro.name,pro.note, pro.price_sell,pro.picture,pro.description,pro.weight,pro.color ");
	    sql.append("FROM         PRODUCT pro ");
	    sql.append("WHERE pro.id = ?1 ");
	    
		Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    query.setParameter(1, idProduct);
	    //TProduct tProduct = (TProduct) query.getSingleResult();
	    //List<TProduct> listProduct=query.getResultList();
	    List<Object[]> listProductObject=query.getResultList();
		List<TProduct> listProduct = new ArrayList<TProduct>();
		for(Object[] obj : listProductObject)
		{
			TProduct tProduct = new TProduct(Integer.parseInt(String.valueOf(obj[0])),String.valueOf(obj[1]),String.valueOf(obj[2]),String.valueOf(obj[3]),String.valueOf(obj[4]),String.valueOf(obj[5]),String.valueOf(obj[6]),String.valueOf(obj[7]));
			listProduct.add(tProduct);
		}
	    return listProduct;
	}

}
