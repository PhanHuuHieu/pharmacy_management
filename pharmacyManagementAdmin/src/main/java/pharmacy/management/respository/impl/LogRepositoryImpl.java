package pharmacy.management.respository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.management.base.repository.BaseRepositoryImpl;
import pharmacy.management.bean.LogBean;
import pharmacy.management.entity.TLog;
import pharmacy.management.entity.TProduct;
import pharmacy.management.respository.LogRepository;

@Repository
public class LogRepositoryImpl extends BaseRepositoryImpl<TLog, Integer> implements LogRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<LogBean> getLog(String idEmployee, String dateTime) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT log.id,log.date_time,log.content,cus.name ");
		sql.append("FROM         LOG log ");
		sql.append("LEFT JOIN EMPLOYEE cus ON cus.id = log.fk_employee_id ");
		sql.append("WHERE 1=1 ");
		if (!idEmployee.equals("-1")) {
			sql.append("AND log.fk_employee_id = " + idEmployee + " ");
		}
		if (!dateTime.equals("")) {
			sql.append("AND log.date_time >= '" + dateTime + "' ");
		}
		sql.append(" ORDER BY log.date_time DESC");

		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listLogObject = query.getResultList();
		List<LogBean> listLog = new ArrayList<LogBean>();
		for (Object[] obj : listLogObject) {
			LogBean tLog = new LogBean(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]),
					String.valueOf(obj[2]), String.valueOf(obj[3]));
			listLog.add(tLog);
		}
		return listLog;
	}

	@Override
	@Transactional
	public void insertLog(String idEmp, String content) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO LOG(fk_employee_id,date_time,content) ");
		sql.append("VALUES(?,GETDATE(),?) ");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, idEmp).setParameter(2, content).executeUpdate();
	}
}
