package pharmacy.management.respository.impl;

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
import pharmacy.management.bean.DeptBean;
import pharmacy.management.bean.OrderBean;
import pharmacy.management.bean.ProductBean;
import pharmacy.management.bean.ReponseBean;
import pharmacy.management.bean.ReportOrderProduct;
import pharmacy.management.bean.StockBean;
import pharmacy.management.entity.TDept;
import pharmacy.management.entity.TOrderProduct;
import pharmacy.management.entity.TProduct;
import pharmacy.management.entity.TProductGroup;
import pharmacy.management.entity.TStock;
import pharmacy.management.form.DeptForm;
import pharmacy.management.form.OrderReportForm;
import pharmacy.management.form.ProductForm;
import pharmacy.management.form.StockForm;
import pharmacy.management.respository.DeptRepository;
import pharmacy.management.respository.OrderProductRepository;
import pharmacy.management.respository.ProductRepository;
import pharmacy.management.respository.StockRepository;

@Repository
public class DeptRepositoryImpl extends BaseRepositoryImpl<TDept, Integer> implements DeptRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<DeptBean> getListDept(DeptForm deptForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT dept.id,supplier_name,money_own,money_paided,convert(varchar,date_start,23) date_start,convert(varchar,date_end,23) date_end,limit,over_limit,dept.id a ");
		sql.append("FROM DEPT dept ");
		sql.append("LEFT JOIN SUPPLIER sup ON sup.id = dept.fk_supplier_id ");
		sql.append("WHERE dept.status = '0' ");
		if (!deptForm.getName_supplier_dept().equals("-1")) {
			sql.append("AND dept.fk_supplier_id = '" + deptForm.getName_supplier_dept() + "' ");
		}
		if (!deptForm.getDate_end_dept().equals("")) {
			sql.append("AND dept.date_end >= '" + deptForm.getDate_end_dept() + "' ");
		}
		if (!deptForm.getDate_start_dept().equals("")) {
			sql.append("AND dept.date_start >= '" + deptForm.getDate_start_dept() + "' ");
		}
//	    if(stockForm.getId()!=null)
//	    {
//	    	sql.append("AND stock.id = '"+stockForm.getId()+"' ");
//	    }
		Query query = entityManager.createNativeQuery(sql.toString());
		List<DeptBean> list = query.getResultList();
		return list;
	}

	@Override
	public List<DeptBean> getDept(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT id,fk_supplier_id,money_own,money_paided,convert(varchar,date_start,23) date_start,convert(varchar,date_end,23),limit,over_limit,term,note ");
		sql.append("FROM DEPT dept ");
		sql.append("WHERE id = " + id);
		Query query = entityManager.createNativeQuery(sql.toString());
		List<DeptBean> list = query.getResultList();
		return list;
	}

	@Override
	@Transactional
	public void deleteDept(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DEPT ");
		sql.append("SET status = '1' ");
		sql.append("WHERE id = ?1");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, id).executeUpdate();
	}

	@Override
	@Transactional
	public void insertDept(TDept tDept) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO DEPT(fk_supplier_id,money_own,money_paided,date_start,date_end,limit,over_limit,term,note,status) ");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,'0') ");
		Query query = entityManager.createNativeQuery(sql.toString());
		String overLimit = "";
		if (tDept.getOver_limit() == null) {
			overLimit = "0";
		} else {
			overLimit = "1";
		}
		query.setParameter(1, tDept.getFk_supplier_id()).setParameter(2, tDept.getMoney_own())
				.setParameter(3, tDept.getMoney_paided()).setParameter(4, tDept.getDate_start())
				.setParameter(5, tDept.getDate_end()).setParameter(6, tDept.getLimit()).setParameter(7, overLimit)
				.setParameter(8, tDept.getTerm()).setParameter(9, tDept.getNote()).executeUpdate();
	}

	@Override
	@Transactional
	public void updateDept(TDept tDept) {
		if (tDept.getOver_limit() == null) {
			tDept.setOver_limit("0");
		} else {
			tDept.setOver_limit("1");
		}
		StringBuilder sql = new StringBuilder();
		sql.append(
				"UPDATE DEPT SET fk_supplier_id = ?1,money_own = ?2,money_paided = ?3,date_start = ?4,date_end = ?5,limit = ?6,over_limit = ?7, term = ?8, note = ?9 ");
		sql.append("WHERE id = ?10");
		Query query = entityManager.createNativeQuery(sql.toString());
		query

				.setParameter(1, tDept.getFk_supplier_id()).setParameter(2, tDept.getMoney_own())
				.setParameter(3, tDept.getMoney_paided()).setParameter(4, tDept.getDate_start())
				.setParameter(5, tDept.getDate_end()).setParameter(6, tDept.getLimit())
				.setParameter(7, tDept.getOver_limit()).setParameter(8, tDept.getTerm())
				.setParameter(9, tDept.getNote()).setParameter(10, tDept.getId()).executeUpdate();
	}

	@Override
	public long countIdDept(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  COUNT(id) ");
		sql.append("FROM    DEPT ");
		sql.append("WHERE id = ?1 ");
		sql.append("AND status = '0' ");

		Query query = entityManager.createQuery(sql.toString());
		query.setParameter(1, id);
		return (long) query.getSingleResult();
	}

	@Override
	public List<DeptBean> getListPayDept(String dateNow) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT dept.id,supplier_name ");
		sql.append("FROM DEPT dept ");
		sql.append("LEFT JOIN SUPPLIER sup ON sup.id = dept.fk_supplier_id ");
		sql.append("WHERE dept.status = '0' ");
		sql.append("AND dept.day_end = ?1 ");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, dateNow);
		List<DeptBean> list = query.getResultList();
		return list;
	}
}
