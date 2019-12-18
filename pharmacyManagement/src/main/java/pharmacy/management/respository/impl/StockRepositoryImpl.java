package pharmacy.management.respository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.bean.StockBean;
import pharmacy.management.entity.TOrderProduct;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TStock;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.form.StockExportForm;
import pharmacy.management.form.StockForm;
import pharmacy.management.respository.OrderProductRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.respository.StockRepository;

@Repository
public class StockRepositoryImpl extends BaseRepositoryImpl<TStock, Integer> implements StockRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void getNameProductFromStock(String stock_name, List<ReponseBean> listData) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT DISTINCT pro_gr.product_group_name ");
	    sql.append("FROM STOCK stock ");
	    sql.append("LEFT JOIN PRODUCT pro ");
	    sql.append("ON pro.id = stock.fk_product_id ");
	    sql.append("LEFT JOIN PRODUCT_GROUP pro_gr ");
	    sql.append("ON pro_gr.id = pro.fk_product_group_id ");
	    sql.append("WHERE 1=1 ");
	    if(!stock_name.equals(""))
	    {
	    	 sql.append("AND stock.stock_name LIKE N'%"+stock_name+"%' ");
	    }
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    List<String> list = query.getResultList();
	    for(String obj : list)
		{
	    	listData.add(new ReponseBean(obj));
		}
		
	}

	@Override
	public void getValueProductFromStock(String stock_name, List<ReponseBean> listData) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT COUNT(pro_gr.product_group_name) ");
	    sql.append("FROM STOCK stock ");
	    sql.append("LEFT JOIN PRODUCT pro ");
	    sql.append("ON pro.id = stock.fk_product_id ");
	    sql.append("LEFT JOIN PRODUCT_GROUP pro_gr ");
	    sql.append("ON pro_gr.id = pro.fk_product_group_id ");
	    sql.append("WHERE 1=1 ");
	    if(!stock_name.equals(""))
	    {
	    	 sql.append("AND stock.stock_name LIKE N'%"+stock_name+"%' ");
	    }
	    sql.append("GROUP BY pro_gr.id");
	    
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    List<Integer> list = query.getResultList();
	    Double sum = 0.0;
	    for(int i=0;i<list.size();i++)
		{
	    	sum+=list.get(i);
		}
	    for(int i=0;i<list.size();i++)
		{
	    	Double value = list.get(i)/sum;
	    	listData.get(i).setY(BigDecimal.valueOf(value));
		}
	}

	@Override
	public List<TStock> getListStock(TStock stockForm) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT type_form,convert(varchar,date,23),id,stock_name,type,fk_product_id,status,status a ");
	    sql.append("FROM STOCK stock ");
	    sql.append("WHERE stock.is_delete = '0' ");
	    if(!stockForm.getStatus().equals("-1"))
	    {
	    	sql.append("AND stock.status = '"+stockForm.getStatus()+"' ");
	    }
	    if(!stockForm.getDate().equals(""))
	    {
	    	sql.append("AND stock.date = '"+stockForm.getDate()+"' ");
	    }
//	    if(stockForm.getId()!=null)
//	    {
//	    	sql.append("AND stock.id = '"+stockForm.getId()+"' ");
//	    }
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    List<TStock> list = query.getResultList();
	    return list;
	}

	@Override
	public List<StockBean> getStock(String id) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT type_form,convert(varchar,date,23),stock_name,type,fk_product_id,fk_employee_id,status,note,id ");
	    sql.append("FROM STOCK stock ");
	    sql.append("WHERE id = "+id);
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    List<StockBean> list = query.getResultList();
	    return list;
	}

	@Override
	@Transactional
	public void deleteStock(String id) {
		StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE STOCK ");
	    sql.append("SET is_delete = '1' ");
	    sql.append("WHERE id = ?1");
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    query
	    .setParameter(1, id)
	    .executeUpdate();
	}

	@Override
	@Transactional
	public void insertStock(TStock stockBean) {
		StringBuilder sql = new StringBuilder();
	    sql.append("INSERT INTO STOCK(type_form,date,stock_name,type,status,fk_employee_id,fk_product_id,note,is_delete) ");
	    sql.append("VALUES(?,?,?,?,?,?,?,?,'0') ");
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    query
	    .setParameter(1, stockBean.getType_form())
	    .setParameter(2, stockBean.getDate())
	    .setParameter(3, stockBean.getStock_name())
	    .setParameter(4, stockBean.getType())
	    .setParameter(5, stockBean.getStatus())
	    .setParameter(6, stockBean.getFk_employee_id())
	    .setParameter(7, stockBean.getFk_product_id())
	    .setParameter(8, stockBean.getNote())
	    .executeUpdate();
	}

	@Override
	@Transactional
	public void updateStock(TStock stockBean) {
		StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE STOCK SET type_form = ?1,date = ?2,stock_name = ?3,type = ?4,status = ?5,fk_employee_id = ?6,fk_product_id = ?7, note = ?8 ");
	    sql.append("WHERE id = ?9");
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
	    query
	    .setParameter(1, stockBean.getType_form())
	    .setParameter(2, stockBean.getDate())
	    .setParameter(3, stockBean.getStock_name())
	    .setParameter(4, stockBean.getType())
	    .setParameter(5, stockBean.getStatus())
	    .setParameter(6, stockBean.getFk_employee_id())
	    .setParameter(7, stockBean.getFk_product_id())
	    .setParameter(8, stockBean.getNote())
	    .setParameter(9, stockBean.getId())
	    .executeUpdate();
	}

	@Override
	public long countIdStock(int id) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT  COUNT(id) ");
	    sql.append("FROM    STOCK ");
	    sql.append("WHERE id = ?1 ");
	    sql.append("AND is_delete = '0' ");
	    
		Query query = entityManager.createQuery(
	    		sql.toString());
	    query.setParameter(1, id);
	    return (long)query.getSingleResult();
	}

	@Override
	public List<StockBean> getListExport(StockExportForm stockExportForm) {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT stock.id,stock.type_form,pro.name,stock.date,stock.status,stock.type,emp.name a ");
	    sql.append("FROM STOCK stock ");
	    sql.append("LEFT JOIN PRODUCT pro ON pro.id = stock.fk_product_id ");
	    sql.append("LEFT JOIN EMPLOYEE emp ON emp.id = stock.fk_employee_id ");
	    sql.append("WHERE stock.is_delete = '0' ");
	    if(!stockExportForm.getType_form_export().equals("-1"))
	    {
	    	 sql.append("AND stock.type_form = "+stockExportForm.getType_form_export());
	    }
	    if(!stockExportForm.getName_stock_export().equals(""))
	    {
	    	sql.append("AND stock.stock_name LIKE N'%"+stockExportForm.getName_stock_export()+"%'");
	    }
	   
	    Query query = entityManager.createNativeQuery(
	    		sql.toString());
		List<Object[]> listReportOrderProductObject=query.getResultList();
		List<StockBean> listReportOrderProduct = new ArrayList<StockBean>();
		for(Object[] obj : listReportOrderProductObject)
		{
			StockBean orderBean = new StockBean(String.valueOf(obj[0]),String.valueOf(obj[1]),String.valueOf(obj[2]),String.valueOf(obj[3]),String.valueOf(obj[4]),String.valueOf(obj[5]),String.valueOf(obj[6]));
			listReportOrderProduct.add(orderBean);
		}
		return listReportOrderProduct;
	}
}
