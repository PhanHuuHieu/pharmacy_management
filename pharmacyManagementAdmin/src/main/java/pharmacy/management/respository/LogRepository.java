package pharmacy.management.respository;

import java.util.List;

import pharmacy.management.base.repository.BaseRepository;
import pharmacy.management.bean.LogBean;
import pharmacy.management.entity.TLog;

public interface LogRepository extends BaseRepository<TLog, Integer> {
	List<LogBean> getLog(String idEmployee, String dateTime);

	void insertLog(String idEmp, String content);
}
